package `in`.bala.bereal.domain.repo.directory

interface DirectoryItemsRepository {
    suspend fun getDirectoryItems(id: String): DirectoryItemsResponse
}