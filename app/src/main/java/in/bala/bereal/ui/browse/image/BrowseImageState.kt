package `in`.bala.bereal.ui.browse.image

sealed class BrowseImageState {
    object Loading : BrowseImageState()
    data class Data(
        val url: String,
        val credentials: String
    ) : BrowseImageState()
}
