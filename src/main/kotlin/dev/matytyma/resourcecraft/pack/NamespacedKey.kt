package dev.matytyma.resourcecraft.pack

import dev.matytyma.resourcecraft.serializer.NamespacedKeySerializer
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
