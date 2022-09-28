package `in`.bala.bereal.ui

sealed class Screen(val route: String) {
    object Home : Screen(route = "home")
    object BrowseDirectory : Screen(route = "browse_directory/{id}/{name}") {
        fun createRoute(id: String, name: String) = "browse_directory/$id/$name"
    }
    object BrowseImage : Screen(route = "browse_image/{id}/{name}") {
        fun createRoute(id: String, name: String) = "browse_image/$id/$name"
    }
}