package org.sopt.at.data.local.remote

import org.sopt.at.data.local.model.request.RequestSignUpDto
import org.sopt.at.data.local.model.response.ResponseSignUpDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AtsoptApi {

    @POST("/api/v1/auth/signup")
    suspend fun postSignUp(
        @Body requestSignUpDto: RequestSignUpDto
    ): ResponseSignUpDto
}