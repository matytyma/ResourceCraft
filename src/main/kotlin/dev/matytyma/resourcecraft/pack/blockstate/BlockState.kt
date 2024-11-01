package dev.matytyma.resourcecraft.pack.blockstate

import dev.matytyma.resourcecraft.pack.NamespacedKey
import kotlinx.serialization.Serializable

@Serializable
data class BlockState(
    val variants: Map<String, BlockStateVariant>,
)

@Serializable
data class BlockStateVariant(
    val model: NamespacedKey,
)
