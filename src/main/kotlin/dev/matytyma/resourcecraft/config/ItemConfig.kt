package dev.matytyma.resourcecraft.config

import dev.matytyma.resourcecraft.serializer.MiniMessageSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import org.bukkit.Material
import java.io.File

@Serializable
data class ItemConfig(
    val material: Material,
    @Contextual
    val texture: File,
    @Serializable(MiniMessageSerializer::class)
    val name: Component? = Component.text(""),
    @Contextual
    val model: File? = null,
)
