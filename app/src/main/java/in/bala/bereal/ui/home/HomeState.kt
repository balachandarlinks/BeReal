package `in`.bala.bereal.ui.home

sealed class HomeState {
    object Loading : HomeState()

    data class Error(
        val message: String
    ) : HomeState()

    data class Data(
        val firstName: String,
        val lastName: String,
        val rootId: String,
        val rootName: String,
        val isRootDir: Boolean,
    ) : HomeState()
}
