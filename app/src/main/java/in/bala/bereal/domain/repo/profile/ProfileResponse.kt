package `in`.bala.bereal.domain.repo.profile

sealed class ProfileResponse {
    data class Success(val profile: Profile) : ProfileResponse()
    data class Error(val throwable: Throwable) : ProfileResponse()
}

