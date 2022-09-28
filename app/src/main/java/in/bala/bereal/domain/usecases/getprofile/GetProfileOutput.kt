package `in`.bala.bereal.domain.usecases.getprofile

import `in`.bala.bereal.domain.repo.profile.Profile

sealed class GetProfileOutput {
    data class Success(val profile: Profile) : GetProfileOutput()
    data class Error(val throwable: Throwable) : GetProfileOutput()
}
