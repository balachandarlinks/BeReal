package `in`.bala.bereal.domain.repo.auth

interface AuthRepository {
    fun getCredentials(): String
}
