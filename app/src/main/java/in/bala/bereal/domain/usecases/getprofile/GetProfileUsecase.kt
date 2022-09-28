package `in`.bala.bereal.domain.usecases.getprofile

import `in`.bala.bereal.domain.repo.profile.ProfileRepository
import `in`.bala.bereal.domain.repo.profile.ProfileResponse
import javax.inject.Inject

class GetProfileUsecase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend fun execute(): GetProfileOutput =
        when (val response = profileRepository.getProfile()) {
            is ProfileResponse.Success -> GetProfileOutput.Success(response.profile)
            is ProfileResponse.Error -> GetProfileOutput.Error(response.throwable)
        }
}
