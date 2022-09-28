package `in`.bala.bereal.domain.repo.profile

interface ProfileRepository {
    suspend fun getProfile(): ProfileResponse
}
