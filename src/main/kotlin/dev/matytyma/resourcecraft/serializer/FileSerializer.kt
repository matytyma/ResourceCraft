package dev.matytyma.resourcecraft.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.io.File

class FilePathSerializer(private val path: File) : KSerializer<File> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("FilePathSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) = File(path, decoder.decodeString())

    override fun serialize(encoder: Encoder, value: File) = throw UnsupportedOperationException()
}
