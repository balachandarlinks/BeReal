package `in`.bala.bereal.ui.home

import `in`.bala.bereal.domain.usecases.getprofile.GetProfileOutput
import `in`.bala.bereal.domain.usecases.getprofile.GetProfileUsecase
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProfileUsecase: GetProfileUsecase
) : ViewModel() {
    val uiState = mutableStateOf<HomeState>(value = HomeState.Loading)

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            uiState.value = HomeState.Loading
            when (val output = getProfileUsecase.execute()) {
                is GetProfileOutput.Success -> {
                    uiState.value = HomeState.Data(
                        firstName = output.profile.firstName,
                        lastName = output.profile.lastName,
                        rootId = output.profile.rootId,
                        rootName = output.profile.rootIdName,
                        isRootDir = output.profile.isRootDir,
                    )
                }
                is GetProfileOutput.Error -> {
                    uiState.value = HomeState.Error(output.throwable.toString())
                }
            }
        }
    }
}