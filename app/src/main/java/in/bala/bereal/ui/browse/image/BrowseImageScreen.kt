@file:OptIn(ExperimentalMaterial3Api::class)

package `in`.bala.bereal.ui.browse.image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun BrowseImageScreen(
    viewModel: BrowseImageViewModel = hiltViewModel(),
    name: String,
    navigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(name) },
                navigationIcon = {
                    IconButton(onClick = { navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        },
    ) {
        Surface(modifier = Modifier.padding(it)) {
            Box(modifier = Modifier.padding(16.dp)) {
                Card {
                    when (val result = viewModel.uiState.value) {
                        BrowseImageState.Loading -> {
                            CircularProgressIndicator()
                        }
                        is BrowseImageState.Data -> {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .addHeader("Authorization", result.credentials)
                                    .data(result.url)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}