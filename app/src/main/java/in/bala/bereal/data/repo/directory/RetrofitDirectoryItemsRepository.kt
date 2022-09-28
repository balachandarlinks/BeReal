package `in`.bala.bereal.data.repo.directory

import `in`.bala.bereal.domain.repo.directory.DirectoryItemsRepository
import `in`.bala.bereal.domain.repo.directory.DirectoryItemsResponse
import javax.inject.Inject

class RetrofitDirectoryItemsRepository @Inject constructor(
    private val directoryItemsService: DirectoryItemsService
) : DirectoryItemsRepository {
    override suspend fun getDirectoryItems(id: String): DirectoryItemsResponse {
        return try {
            val response = directoryItemsService.getDirectoryItems(id)
            if (response.isSuccessful) {
                DirectoryItemsResponse.Success(
                    response.body()!!.map { it.toDirectoryItem() }
                )
            } else {
                /**
                 * Error response can be parsed here to provide more meaningful error
                 * message to the user.
                 */
                DirectoryItemsResponse.Error(Throwable(response.errorBody()?.string()))
            }
        } catch (exception: Throwable) {
            DirectoryItemsResponse.Error(exception)
        }
    }
}
