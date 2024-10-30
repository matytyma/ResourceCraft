package dev.matytyma.opencraft

import dev.matytyma.opencraft.pack.NamespacedKey
import dev.matytyma.opencraft.pack.PackMeta
import dev.matytyma.opencraft.pack.blockstate.BlockState
import dev.matytyma.opencraft.pack.blockstate.BlockStateVariant
import dev.matytyma.opencraft.pack.model.*
import dev.matytyma.opencraft.version.ServerVersion
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class OpenCraft : JavaPlugin(), Listener {
    override fun onEnable() {
        val file = File(dataFolder, "pack.zip")
        ZipOutputStream(BufferedOutputStream(FileOutputStream(file))).use { zos ->
            val packMeta = PackMeta("A cool pack", ServerVersion.fromVersionString(server.minecraftVersion).packFormat)
            zos.addJsonFile("pack.mcmeta", packMeta)
            File(dataFolder, "pack.png").let {
                if (!it.exists()) return@let
                zos.addFile("pack.png", File(dataFolder, "pack.png").readBytes())
                zos.addFile("assets/minecraft/textures/item/matytyma.png", it.readBytes())
            }

            File(dataFolder, "rick.png").let {
                if (!it.exists()) return@let
                zos.addFile("assets/minecraft/textures/block/rick.png", it.readBytes())
            }

            zos.addJsonFile(
                "assets/minecraft/models/item/matytyma.json",
                ItemModel(
                    NamespacedKey.minecraft("item/diamond"),
                    mapOf("layer0" to NamespacedKey.minecraft("item/matytyma"))
                )
            )

            zos.addJsonFile(
                "assets/minecraft/models/item/diamond.json",
                ItemModel(
                    NamespacedKey.minecraft("item/generated"),
                    mapOf("layer0" to NamespacedKey.minecraft("item/diamond")),
                    listOf(
                        ModelOverride(
                            ItemPredicate(1),
                            NamespacedKey.minecraft("item/matytyma")
                        )
                    )
                )
            )

            zos.addJsonFile(
                "assets/minecraft/blockstates/note_block.json",
                BlockState(mapOf("instrument=didgeridoo" to BlockStateVariant(NamespacedKey.minecraft("block/rick"))))
            )

            zos.addJsonFile(
                "assets/minecraft/models/block/rick.json",
                BlockModel(
                    NamespacedKey.minecraft("block/decorated_pot"),
                    NamespacedKey.minecraft("block/rick").let {
                        mapOf("particle" to it, "all" to it, "texture" to it)
                    }
                )
            )

            zos.addJsonFile("assets/minecraft/textures/block/rick.png.mcmeta", TextureMeta(TextureAnimation(2)))
        }
        file.copyTo(File(System.getenv("PACK_PATH")), true)
    }
}

private fun ZipOutputStream.addFile(name: String, content: ByteArray) {
    putNextEntry(ZipEntry(name))
    write(content)
    closeEntry()
}

private fun ZipOutputStream.addFile(name: String, content: String) = addFile(name, content.toByteArray())

private inline fun <reified T> ZipOutputStream.addJsonFile(name: String, content: T) =
    addFile(name, Json.encodeToString<T>(content))


@Serializable
data class TextureMeta(
    val animation: TextureAnimation,
)

@Serializable
data class TextureAnimation(
    val frametime: Int,
)
