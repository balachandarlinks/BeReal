package `in`.bala.bereal.data.repo.directory

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DirectoryItemsService {
    @GET("/items/{id}")
    suspend fun getDirectoryItems(@Path("id") id: String): Response<List<DirectoryItemNetworkModel>>
}
