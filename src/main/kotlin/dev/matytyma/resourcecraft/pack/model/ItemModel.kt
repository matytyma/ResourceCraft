package dev.matytyma.resourcecraft.pack.model

import dev.matytyma.resourcecraft.pack.NamespacedKey
import dev.matytyma.resourcecraft.serializer.NamespacedKeyMapSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemModel(
    override val parent: NamespacedKey,
    @Serializable(NamespacedKeyMapSerializer::class)
    override val textures: Map<String, NamespacedKey>,
    val overrides: MutableList<ModelOverride<ItemPredicate>> = mutableListOf(),
) : Model

@Serializable
data class ItemPredicate(
    @SerialName("custom_model_data")
    val customModelData: Int,
) : Predicate
