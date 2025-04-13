package org.sopt.at.database.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.sopt.at.database.source.LocalUserDataSource
import org.sopt.at.model.UserInfo
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION),
    LocalUserDataSource {

    override fun insertUser(user: UserInfo): Boolean {
        val database = this.writableDatabase
        val values = ContentValues().apply {
            put("id", user.id)
            put("pw", user.pw)
        }
        return database.insert("user_info", null, values) != -1L
    }

    override fun getUser(user: UserInfo): Boolean {
        val database = this.readableDatabase
        val cursor = database.query(
            "user_info",
            arrayOf("id", "pw"),
            "id = ? AND pw = ?",
            arrayOf(user.id, user.pw),
            null,
            null,
            null
        )

        return cursor.moveToFirst()
    }

    override fun deleteUser(user: UserInfo): Int {
        val database = this.writableDatabase
        return database.delete(
            "user_info",
            "id = ?",
            arrayOf(user.id)
        )
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS user_info")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "AtsoptDataBase"
        private const val DATABASE_VERSION = 1

        private const val CREATE_USER_TABLE = """
            CREATE TABLE user_info (
                id TEXT PRIMARY KEY,
                pw TEXT NOT NULL
            )
        """
    }
}