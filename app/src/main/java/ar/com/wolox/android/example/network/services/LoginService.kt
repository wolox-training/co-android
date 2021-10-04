package ar.com.wolox.android.example.network.services

import ar.com.wolox.android.example.model.DataUserResponse
import ar.com.wolox.android.example.model.LoginData
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body

interface LoginService {

    @POST("/auth/sign_in")
    suspend fun login(@Body body: LoginData): Response<DataUserResponse>
}