package dev.matytyma.opencraft.pack.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemModel(
    val parent: String,
    val textures: Map<String, String>,
    val overrides: List<ModelOverride>? = null,
)
