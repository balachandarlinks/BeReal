package `in`.bala.bereal.ui

import `in`.bala.bereal.ui.browse.directory.BrowseDirectoryScreen
import `in`.bala.bereal.ui.browse.image.BrowseImageScreen
import `in`.bala.bereal.ui.home.HomeScreen
import `in`.bala.bereal.ui.theme.BeRealTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeRealTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(showBrowseDirectory = { id, name ->
                navController.navigate(Screen.BrowseDirectory.createRoute(id, name))
            })
        }
        composable(route = Screen.BrowseDirectory.route) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            requireNotNull(name) { "Cannot browse a directory without a valid name. Name = $name." }
            BrowseDirectoryScreen(
                name = name,
                showBrowseDirectory = { nestedDirId, nestedDirName ->
                    navController.navigate(
                        Screen.BrowseDirectory.createRoute(
                            nestedDirId,
                            nestedDirName
                        )
                    )
                },
                showBrowseImage = { nestedDirId, nestedDirName ->
                    navController.navigate(
                        Screen.BrowseImage.createRoute(
                            nestedDirId,
                            nestedDirName
                        )
                    )
                },
                navigateUp = { navController.navigateUp() }
            )
        }
        composable(route = Screen.BrowseImage.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            val name = backStackEntry.arguments?.getString("name")
            requireNotNull(id) { "Cannot open image without a valid id. Id = $id." }
            requireNotNull(name) { "Cannot open image without a valid name. Nmae = $name." }
            BrowseImageScreen(
                name = name,
                navigateUp = { navController.navigateUp() }
            )
        }
    }
}
