package dev.matytyma.resourcecraft.pack.texture

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TextureMeta(
    val texture: TextureProperties? = null,
    val animation: AnimationProperties? = null,
)

@Serializable
data class TextureProperties(
    val blur: Boolean? = null,
    val clamp: Boolean? = null,
)

@Serializable
data class AnimationProperties(
    @SerialName("frametime")
    val frameTime: Int? = null,
    val interpolate: Boolean? = null,
    val frames: List<Int>? = null,
    val width: Int? = null,
    val height: Int? = null,
)
