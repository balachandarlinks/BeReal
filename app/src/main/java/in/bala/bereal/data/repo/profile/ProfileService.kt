package `in`.bala.bereal.data.repo.profile

import retrofit2.Response
import retrofit2.http.GET

interface ProfileService {
    @GET("/me")
    suspend fun getProfile(): Response<ProfileNetworkModel>
}
