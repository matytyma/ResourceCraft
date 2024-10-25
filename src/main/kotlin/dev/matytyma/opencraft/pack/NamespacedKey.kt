package dev.matytyma.opencraft.pack

import dev.matytyma.opencraft.serializer.NamespacedKeySerializer
import kotlinx.serialization.Serializable

@Serializable(NamespacedKeySerializer::class)
data class NamespacedKey(
    val namespace: String,
    val key: String,
) {
    companion object {
        fun minecraft(key: String) = NamespacedKey("minecraft", key)
    }
}
