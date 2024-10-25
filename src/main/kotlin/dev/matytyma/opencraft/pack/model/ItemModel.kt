package dev.matytyma.opencraft.pack.model

import dev.matytyma.opencraft.pack.NamespacedKey
import dev.matytyma.opencraft.serializer.NamespacedKeyMapSerializer
import kotlinx.serialization.Serializable

@Serializable
data class ItemModel(
    val parent: NamespacedKey,
    @Serializable(NamespacedKeyMapSerializer::class)
    val textures: Map<String, NamespacedKey>,
    val overrides: List<ModelOverride>? = null,
)
