package dev.matytyma.opencraft.serializer

import dev.matytyma.opencraft.version.PackFormat
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.IntArraySerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

private fun deserializePackFormat(packFormatNumber: Int) =
    PackFormat.entries.first { it.representation == packFormatNumber }

object PackFormatSerializer : KSerializer<PackFormat> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("PackFormatSerializer", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder) = deserializePackFormat(decoder.decodeInt())

    override fun serialize(encoder: Encoder, value: PackFormat) = encoder.encodeInt(value.representation)
}

object PackFormatRangeSerializer : KSerializer<ClosedRange<PackFormat>> {
    private val delegate = IntArraySerializer()

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor
        get() = SerialDescriptor("IntRangeAsArraySerializer", delegate.descriptor)

    override fun deserialize(decoder: Decoder) =
        delegate.deserialize(decoder).map(::deserializePackFormat).let { it[0]..it[1] }

    override fun serialize(encoder: Encoder, value: ClosedRange<PackFormat>) =
        delegate.serialize(encoder, intArrayOf(value.start.representation, value.endInclusive.representation))
}
