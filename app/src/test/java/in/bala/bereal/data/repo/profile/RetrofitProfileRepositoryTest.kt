package `in`.bala.bereal.data.repo.profile

import `in`.bala.bereal.domain.repo.profile.Profile
import `in`.bala.bereal.domain.repo.profile.ProfileResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class RetrofitProfileRepositoryTest {
    private val profileService = mockk<ProfileService>()
    private val retrofitProfileRepository = RetrofitProfileRepository(profileService)

    @Test
    fun `returns profile when profile service returns profile successfully`() = runBlocking {
        val expectedProfile = Profile(
            firstName = "Balachandar",
            lastName = "Kolathur Mani",
            isRootDir = true,
            rootId = "1",
            rootIdName = "Root Item",
        )
        val profileNetworkModel = ProfileNetworkModel(
            firstName = "Balachandar",
            lastName = "Kolathur Mani",
            rootItem = RootItemNetworkModel(
                id = "1",
                name = "Root Item",
                isDir = true,
            )
        )
        val response = Response.success(profileNetworkModel)
        coEvery { profileService.getProfile() } returns response

        val result = retrofitProfileRepository.getProfile()

        assertEquals(expectedProfile, (result as ProfileResponse.Success).profile)
    }

    @Test
    fun `returns error when profile service returns error`() = runBlocking {
        val expectedError = Throwable("error")
        val response = Response.error<ProfileNetworkModel>(400, "error".toResponseBody())
        coEvery { profileService.getProfile() } returns response

        val result = retrofitProfileRepository.getProfile()

        assertEquals(expectedError.message, (result as ProfileResponse.Error).throwable.message)
    }

    @Test
    fun `returns error when profile service throws exception`() = runBlocking {
        val expectedError = Throwable("error")
        coEvery { profileService.getProfile() } throws expectedError

        val result = retrofitProfileRepository.getProfile()

        assertEquals(expectedError, (result as ProfileResponse.Error).throwable)
    }
}
