package `in`.bala.bereal.data.repo.auth

import `in`.bala.bereal.domain.repo.auth.AuthRepository
import okhttp3.Credentials
import javax.inject.Inject

class LocalAuthRepository @Inject constructor() : AuthRepository {
    override fun getCredentials(): String = Credentials.basic("noel", "foobar")
}