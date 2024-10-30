package dev.matytyma.opencraft.pack.model

import dev.matytyma.opencraft.pack.NamespacedKey
import dev.matytyma.opencraft.serializer.NamespacedKeyMapSerializer
import kotlinx.serialization.Serializable

@Serializable
data class BlockModel(
    override val parent: NamespacedKey,
    @Serializable(NamespacedKeyMapSerializer::class)
    override val textures: Map<String, NamespacedKey>,
) : Model
