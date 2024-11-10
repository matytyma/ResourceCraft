package dev.matytyma.resourcecraft.util

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

fun ZipOutputStream.addFile(name: String, content: ByteArray) {
    putNextEntry(ZipEntry(name))
    write(content)
    closeEntry()
}

fun ZipOutputStream.addFile(name: String, content: String) = addFile(name, content.toByteArray())

fun ZipOutputStream.addFile(file: File, name: String = file.name) = addFile(name, file.readBytes())

inline fun <reified T> ZipOutputStream.addJsonFile(name: String, content: T) =
    addFile(name, Json.encodeToString<T>(content))
