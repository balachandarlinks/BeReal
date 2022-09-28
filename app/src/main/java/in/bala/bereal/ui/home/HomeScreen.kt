@file:OptIn(ExperimentalMaterial3Api::class)

package `in`.bala.bereal.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    showBrowseDirectory: (id: String, name: String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BeReal") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        },
    ) {
        val state = viewModel.uiState.value
        Surface(modifier = Modifier.padding(it)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    if (state is HomeState.Loading)
                        CircularProgressIndicator()
                    if (state is HomeState.Data) {
                        Text(state.firstName)
                        Text(state.lastName)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            enabled = state.isRootDir,
                            onClick = { showBrowseDirectory(state.rootId, state.rootName) }) {
                            Text("Browse ${state.rootName}")
                        }
                    }
                    if (state is HomeState.Error)
                        Text(state.message)
                }
            }
        }
    }
}