package dev.matytyma.resourcecraft.pack.model

import dev.matytyma.resourcecraft.pack.NamespacedKey
import dev.matytyma.resourcecraft.serializer.NamespacedKeyMapSerializer
import kotlinx.serialization.Serializable

@Serializable
sealed interface Model {
    val parent: NamespacedKey

    @Serializable(NamespacedKeyMapSerializer::class)
    val textures: Map<String, NamespacedKey>
}

@Serializable
data class ModelOverride<T : Predicate>(
    val predicate: T,
    val model: NamespacedKey,
)

@Serializable
sealed interface Predicate
