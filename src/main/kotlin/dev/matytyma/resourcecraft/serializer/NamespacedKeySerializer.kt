package dev.matytyma.resourcecraft.serializer

import dev.matytyma.resourcecraft.pack.NamespacedKey
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object NamespacedKeySerializer : KSerializer<NamespacedKey> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("NamespacedKeySerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) = throw UnsupportedOperationException()

    override fun serialize(encoder: Encoder, value: NamespacedKey) = encoder.encodeString("${value.namespace}:${value.key}")
}

object NamespacedKeyMapSerializer : KSerializer<Map<String, NamespacedKey>> {
    private val delegate = MapSerializer(String.serializer(), NamespacedKeySerializer)

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor
        get() = SerialDescriptor("NamespacedKeyMapSerializer", delegate.descriptor)

    override fun deserialize(decoder: Decoder) = throw UnsupportedOperationException()

    override fun serialize(encoder: Encoder, value: Map<String, NamespacedKey>) = delegate.serialize(encoder, value)
}
