package `in`.bala.bereal.domain.usecases.getcredentials

sealed class GetCredentialsOutput {
    data class Success(val credentials: String) : GetCredentialsOutput()
    data class Error(val throwable: Throwable) : GetCredentialsOutput()
}
