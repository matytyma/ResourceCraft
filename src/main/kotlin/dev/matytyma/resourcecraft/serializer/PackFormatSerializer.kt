package dev.matytyma.resourcecraft.serializer

import dev.matytyma.resourcecraft.version.PackFormat
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.IntArraySerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object PackFormatSerializer : KSerializer<PackFormat> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("PackFormatSerializer", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder) = throw UnsupportedOperationException()

    override fun serialize(encoder: Encoder, value: PackFormat) = encoder.encodeInt(value.value)
}

object PackFormatRangeSerializer : KSerializer<ClosedRange<PackFormat>> {
    private val delegate = IntArraySerializer()

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor
        get() = SerialDescriptor("IntRangeAsArraySerializer", delegate.descriptor)

    override fun deserialize(decoder: Decoder) = throw UnsupportedOperationException()

    override fun serialize(encoder: Encoder, value: ClosedRange<PackFormat>) =
        delegate.serialize(encoder, intArrayOf(value.start.value, value.endInclusive.value))
}
