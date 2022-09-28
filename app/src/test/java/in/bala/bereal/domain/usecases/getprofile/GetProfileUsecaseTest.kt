package `in`.bala.bereal.domain.usecases.getprofile

import `in`.bala.bereal.domain.repo.profile.Profile
import `in`.bala.bereal.domain.repo.profile.ProfileRepository
import `in`.bala.bereal.domain.repo.profile.ProfileResponse
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetProfileUsecaseTest {
    private val profileRepository = mockk<ProfileRepository>()
    private val getProfileUsecase = GetProfileUsecase(profileRepository)

    @Test
    fun `return profile on success`() = runBlocking {
        val profile = mockk<Profile>()
        val profileResponse = ProfileResponse.Success(profile)
        coEvery { profileRepository.getProfile() } returns profileResponse

        val output = getProfileUsecase.execute()

        assertEquals(profile, (output as GetProfileOutput.Success).profile)
    }

    @Test
    fun `return error on failure`() = runBlocking {
        val error = Throwable("Error")
        val profileResponse = ProfileResponse.Error(error)
        coEvery { profileRepository.getProfile() } returns profileResponse

        val output = getProfileUsecase.execute()

        assertEquals(error, (output as GetProfileOutput.Error).throwable)
    }

}