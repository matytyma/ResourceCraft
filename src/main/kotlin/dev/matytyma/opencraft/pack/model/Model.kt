package dev.matytyma.opencraft.pack.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModelOverride(
    val predicate: Predicate,
    val model: String,
)

@Serializable
data class Predicate(
    @SerialName("custom_model_data")
    val customModelData: Int,
)
