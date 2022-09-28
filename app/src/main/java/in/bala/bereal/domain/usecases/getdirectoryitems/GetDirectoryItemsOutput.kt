package `in`.bala.bereal.domain.usecases.getdirectoryitems

import `in`.bala.bereal.domain.repo.directory.DirectoryItem

sealed class GetDirectoryItemsOutput {
    data class Success(val items: List<DirectoryItem>) : GetDirectoryItemsOutput()
    data class Error(val throwable: Throwable) : GetDirectoryItemsOutput()
}
