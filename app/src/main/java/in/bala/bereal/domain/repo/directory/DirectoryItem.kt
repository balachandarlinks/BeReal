package `in`.bala.bereal.domain.repo.directory

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DirectoryItem(
    val id: String,
    val name: String,
    val isDir: Boolean,
    val contentType: String?,
) : Parcelable

fun DirectoryItem.isImage(): Boolean = contentType?.startsWith("image/") ?: false
