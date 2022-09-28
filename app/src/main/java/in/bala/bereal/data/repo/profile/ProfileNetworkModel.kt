package `in`.bala.bereal.data.repo.profile

import `in`.bala.bereal.domain.repo.profile.Profile
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileNetworkModel(
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
    @Json(name = "rootItem") val rootItem: RootItemNetworkModel,
)

@JsonClass(generateAdapter = true)
data class RootItemNetworkModel(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "isDir") val isDir: Boolean
)

fun ProfileNetworkModel.toProfile(): Profile {
    return Profile(
        firstName = this.firstName,
        lastName = this.lastName,
        rootId = this.rootItem.id,
        isRootDir = this.rootItem.isDir,
        rootIdName = this.rootItem.name
    )
}
