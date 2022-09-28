package `in`.bala.bereal.domain.usecases.getcredentials

import `in`.bala.bereal.domain.repo.auth.AuthRepository
import javax.inject.Inject

class GetCredentialsUsecase @Inject constructor(
    private val authRepo: AuthRepository
) {
    fun execute(): String = authRepo.getCredentials()
}
