package dev.matytyma.resourcecraft.pack.model

import dev.matytyma.resourcecraft.pack.NamespacedKey
import dev.matytyma.resourcecraft.serializer.NamespacedKeyMapSerializer
import kotlinx.serialization.Serializable

@Serializable
data class BlockModel(
    override val parent: NamespacedKey,
    @Serializable(NamespacedKeyMapSerializer::class)
    override val textures: Map<String, NamespacedKey>,
) : Model
