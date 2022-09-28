@file:OptIn(ExperimentalMaterial3Api::class)

package `in`.bala.bereal.ui.browse.directory

import `in`.bala.bereal.domain.repo.directory.DirectoryItem
import `in`.bala.bereal.domain.repo.directory.isImage
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BrowseDirectoryScreen(
    viewModel: BrowseDirectoryViewModel = hiltViewModel(),
    name: String,
    showBrowseDirectory: (String, String) -> Unit,
    showBrowseImage: (String, String) -> Unit,
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
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                when (val state = viewModel.uiState.value) {
                    BrowseDirectoryState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is BrowseDirectoryState.Data -> {
                        if (state.data.isNotEmpty())
                            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                                items(state.data) { item ->
                                    DirectoryItemCard(
                                        item = item,
                                        showBrowseDirectory = showBrowseDirectory,
                                        showBrowseImage = showBrowseImage
                                    )
                                }
                            }
                        else
                            Text("No data found", modifier = Modifier.padding(16.dp))
                    }
                    is BrowseDirectoryState.Error -> {
                        Text(text = state.message)
                    }
                }
            }
        }
    }
}

@Composable
fun DirectoryItemCard(
    item: DirectoryItem,
    showBrowseDirectory: (String, String) -> Unit,
    showBrowseImage: (String, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(item.name)
            Text(item.id, style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                enabled = item.isDir || item.isImage(),
                onClick = {
                    if (item.isDir) {
                        showBrowseDirectory(
                            item.id,
                            item.name
                        )
                    } else if (item.isImage()) {
                        showBrowseImage(item.id, item.name)
                    } else {
                        throw IllegalStateException("Unsupported type - $item")
                    }
                }) {
                Text("Browse ${item.name}")
            }
        }
    }
}