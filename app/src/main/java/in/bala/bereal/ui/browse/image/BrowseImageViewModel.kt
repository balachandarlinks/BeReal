package `in`.bala.bereal.ui.browse.image

import `in`.bala.bereal.domain.usecases.getcredentials.GetCredentialsUsecase
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BrowseImageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCredentialsUsecase: GetCredentialsUsecase
) : ViewModel() {
    val uiState = mutableStateOf<BrowseImageState>(BrowseImageState.Loading)

    init {
        val id = savedStateHandle.get<String>("id")
        requireNotNull(id) { "Cannot open image without valid id. Id = $id." }
        val credentials = getCredentialsUsecase.execute()
        uiState.value = BrowseImageState.Data(
            url = "http://163.172.147.216:8080/items/$id/data",
            credentials = credentials
        )
    }
}