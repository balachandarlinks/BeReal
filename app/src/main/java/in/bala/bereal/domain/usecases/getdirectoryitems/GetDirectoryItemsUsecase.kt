package `in`.bala.bereal.domain.usecases.getdirectoryitems

import `in`.bala.bereal.domain.repo.directory.DirectoryItemsRepository
import `in`.bala.bereal.domain.repo.directory.DirectoryItemsResponse
import javax.inject.Inject

class GetDirectoryItemsUsecase @Inject constructor(
    private val getDirectoryItemsRepository: DirectoryItemsRepository
) {
    suspend fun execute(id: String): GetDirectoryItemsOutput =
        when (val response = getDirectoryItemsRepository.getDirectoryItems(id)) {
            is DirectoryItemsResponse.Success -> GetDirectoryItemsOutput.Success(response.items)
            is DirectoryItemsResponse.Error -> GetDirectoryItemsOutput.Error(response.throwable)
        }
}
