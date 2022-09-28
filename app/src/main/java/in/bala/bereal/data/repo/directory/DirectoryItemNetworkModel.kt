package `in`.bala.bereal.data.repo.directory

import `in`.bala.bereal.domain.repo.directory.DirectoryItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DirectoryItemNetworkModel(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "isDir") val isDir: Boolean,
    @Json(name = "contentType") val contentType: String?,
)

fun DirectoryItemNetworkModel.toDirectoryItem(): DirectoryItem {
    return DirectoryItem(
        id = id,
        name = name,
        isDir = isDir,
        contentType = contentType
    )
}
