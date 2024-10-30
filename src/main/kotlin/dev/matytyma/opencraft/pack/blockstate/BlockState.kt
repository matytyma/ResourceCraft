package dev.matytyma.opencraft.pack.blockstate

import dev.matytyma.opencraft.pack.NamespacedKey
import kotlinx.serialization.Serializable

@Serializable
data class BlockState(
    val variants: Map<String, BlockStateVariant>,
)

@Serializable
data class BlockStateVariant(
    val model: NamespacedKey,
)
