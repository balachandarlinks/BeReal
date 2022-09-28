package `in`.bala.bereal.ui.browse.directory

import `in`.bala.bereal.domain.usecases.getdirectoryitems.GetDirectoryItemsOutput
import `in`.bala.bereal.domain.usecases.getdirectoryitems.GetDirectoryItemsUsecase
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrowseDirectoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDirectoryItemsUsecase: GetDirectoryItemsUsecase
) : ViewModel() {
    val uiState = mutableStateOf<BrowseDirectoryState>(value = BrowseDirectoryState.Loading)

    init {
        val id = savedStateHandle.get<String>("id")
        requireNotNull(id) { "Cannot browse a directory without a valid id. Id = $id." }
        loadDirectoryItems(id)
    }

    private fun loadDirectoryItems(id: String) {
        uiState.value = BrowseDirectoryState.Loading
        viewModelScope.launch {
            when(val output = getDirectoryItemsUsecase.execute(id)) {
                is GetDirectoryItemsOutput.Success -> {
                    uiState.value = BrowseDirectoryState.Data(output.items)
                }
                is GetDirectoryItemsOutput.Error -> {
                    uiState.value = BrowseDirectoryState.Error(output.throwable.toString())
                }
            }
        }
    }
}