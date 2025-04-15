package org.sopt.at.database.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.sopt.at.database.source.HomeBannerDataSource
import org.sopt.at.model.BannerInfo
import javax.inject.Inject

class HomeBannerDataSourceImpl @Inject constructor(private val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION),
    HomeBannerDataSource {

    override fun insertBanner(category: String, title: String, image: String): Boolean {
        val database = this.writableDatabase
        val values = ContentValues()
        values.put("category", category)
        values.put("title", title)
        values.put("image", image)
        return database.insert("banner_info", null, values) != -1L
    }

    override fun getBanner(category: String): List<BannerInfo> {
        val database = this.readableDatabase
        val bannerList = ArrayList<BannerInfo>()

        val cursor = database.query(
            "banner_info",
            arrayOf("title", "image"),
            "category = ?",
            arrayOf(category),
            null,
            null,
            null
        )

        cursor.use {
            while (it.moveToNext()) {
                val title = it.getString(it.getColumnIndexOrThrow("title"))
                val imageName = it.getString(it.getColumnIndexOrThrow("image"))

                val resourceId = context.resources.getIdentifier(
                    imageName,
                    "drawable",
                    "org.sopt.at.designsystem"
                )

                bannerList.add(BannerInfo(title, resourceId))
            }
        }

        return bannerList
    }

    override fun updateFavorite(title: String, isFavorite: Boolean): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("favorite", if (isFavorite) 1 else 0)
        }
        val updatedRows = db.update(
            "banner_info",
            values,
            "title = ?",
            arrayOf(title)
        )
        return updatedRows > 0
    }

    override fun getFavoriteBanners(): List<BannerInfo> {
        val database = this.readableDatabase
        val bannerList = mutableListOf<BannerInfo>()

        val cursor = database.query(
            "banner_info",
            arrayOf("title", "image"),
            "favorite = ?",
            arrayOf("1"),
            null,
            null,
            null
        )

        cursor.use {
            while (it.moveToNext()) {
                val title = it.getString(it.getColumnIndexOrThrow("title"))
                val imageName = it.getString(it.getColumnIndexOrThrow("image"))

                val resourceId = context.resources.getIdentifier(
                    imageName,
                    "drawable",
                    "org.sopt.at.designsystem"
                )

                bannerList.add(BannerInfo(title, resourceId))
            }
        }

        return bannerList
    }

    override fun deleteAll(): Boolean {
        val database = this.writableDatabase
        val deletedRows = database.delete("banner_info", null, null)
        return deletedRows > 0
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_BANNER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS user_info")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "HomeBanner"
        private const val DATABASE_VERSION = 1

        private const val CREATE_BANNER_TABLE = """
            CREATE TABLE banner_info (
                category TEXT NOT NULL,         
                title TEXT NOT NULL,            
                image TEXT NOT NULL,
                favorite INTEGER NOT NULL DEFAULT 0
    ) 
        """
    }
}
