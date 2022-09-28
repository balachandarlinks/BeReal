package `in`.bala.bereal.data.repo.profile

import `in`.bala.bereal.domain.repo.profile.ProfileRepository
import `in`.bala.bereal.domain.repo.profile.ProfileResponse
import javax.inject.Inject

class RetrofitProfileRepository @Inject constructor(
    private val profileService: ProfileService
) : ProfileRepository {
    override suspend fun getProfile(): ProfileResponse {
        return try {
            val response = profileService.getProfile()
            if (response.isSuccessful) {
                ProfileResponse.Success(
                    response.body()!!.toProfile()
                )
            } else {
                /**
                 * Error response can be parsed here to provide more meaningful error
                 * message to the user.
                 */
                ProfileResponse.Error(Throwable(response.errorBody()?.string()))
            }
        } catch (exception: Throwable) {
            ProfileResponse.Error(exception)
        }
    }
}
