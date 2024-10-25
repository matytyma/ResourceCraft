package dev.matytyma.opencraft.pack.model

import dev.matytyma.opencraft.pack.NamespacedKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModelOverride(
    val predicate: Predicate,
    val model: NamespacedKey,
)

@Serializable
data class Predicate(
    @SerialName("custom_model_data")
    val customModelData: Int,
)
