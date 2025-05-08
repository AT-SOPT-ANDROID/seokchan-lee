package org.sopt.at.data.local.remote

import org.sopt.at.data.local.model.request.SignInRequestDto
import org.sopt.at.data.local.model.request.SignUpRequestDto
import org.sopt.at.data.local.model.response.MyNicknameResponseDto
import org.sopt.at.data.local.model.response.SignInResponseDto
import org.sopt.at.data.local.model.response.SignUpResponseDto
import org.sopt.at.data.local.model.response.UserNicknameResponseDto
import org.sopt.at.network.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AtsoptApi {

    @POST("/api/v1/auth/signup")
    suspend fun postSignUp(
        @Body signUpRequestDto: SignUpRequestDto
    ): BaseResponse<SignUpResponseDto>

    @POST("/api/v1/auth/signin")
    suspend fun postSignIn(
        @Body signInRequestDto: SignInRequestDto
    ): BaseResponse<SignInResponseDto>

    @GET("/api/v1/users/me")
    suspend fun getMyNickname(
        @Header("userId") userId: Long
    ): BaseResponse<MyNicknameResponseDto>

    @GET("/api/v1/users")
    suspend fun getUserNickname(
        @Query("keyword") keyword: String
    ): BaseResponse<UserNicknameResponseDto>
}
