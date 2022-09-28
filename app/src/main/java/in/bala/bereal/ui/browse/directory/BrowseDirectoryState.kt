package `in`.bala.bereal.ui.browse.directory

import `in`.bala.bereal.domain.repo.directory.DirectoryItem

sealed class BrowseDirectoryState {
    object Loading: BrowseDirectoryState()
    data class Data(val data: List<DirectoryItem>): BrowseDirectoryState()
    data class Error(val message: String): BrowseDirectoryState()
}
