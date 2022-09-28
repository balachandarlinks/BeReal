package `in`.bala.bereal.domain.repo.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    val firstName: String,
    val lastName: String,
    val rootId: String,
    val isRootDir: Boolean,
    val rootIdName: String
) : Parcelable
