package dev.matytyma.opencraft

import dev.matytyma.opencraft.pack.PackMeta
import dev.matytyma.opencraft.pack.model.*
import dev.matytyma.opencraft.version.PackFormat
import dev.matytyma.opencraft.version.ServerVersion
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
                if (it.exists()) {
                    zos.addFile("pack.png", File(dataFolder, "pack.png").readBytes())
                    zos.addFile("assets/minecraft/textures/item/matytyma.png", File(dataFolder, "pack.png").readBytes())
                }
            }
            zos.addJsonFile("assets/minecraft/models/item/matytyma.json", ItemModel("minecraft:item/diamond", mapOf("layer0" to "minecraft:item/matytyma")))
            zos.addJsonFile(
                "assets/minecraft/models/item/diamond.json",
                ItemModel(
                    "minecraft:item/generated",
                    mapOf("layer0" to "minecraft:item/diamond"),
                    listOf(ModelOverride(
                        Predicate(1),
                        "minecraft:item/matytyma"
                    ))
                )
            )
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

private inline fun <reified T> ZipOutputStream.addJsonFile(name: String, content: T) = addFile(name, Json.encodeToString<T>(content))
