package `in`.bala.bereal.domain.repo.directory

sealed class DirectoryItemsResponse {
    data class Success(val items: List<DirectoryItem>) : DirectoryItemsResponse()
    data class Error(val throwable: Throwable) : DirectoryItemsResponse()
}

