package ar.com.wolox.android.example.network.services

import ar.com.wolox.android.example.model.NewDetailResponse
import ar.com.wolox.android.example.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("/comments")
    suspend fun getNews(@Query("page") page: Int): Response<NewsResponse>

    @GET("/comments/{id}")
    suspend fun getInformationNew(@Path("id") id: Int): Response<NewDetailResponse>

    @PUT("/comments/like/{id}")
    suspend fun toggleLiked(@Path("id") id: Int): Response<Unit>
}
