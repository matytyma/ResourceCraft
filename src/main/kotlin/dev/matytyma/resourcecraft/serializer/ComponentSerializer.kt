package dev.matytyma.resourcecraft.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

object MiniMessageSerializer : KSerializer<Component> {
    private val mm = MiniMessage.miniMessage()

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("MiniMessageSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) = mm.deserialize(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: Component) = encoder.encodeString(mm.serialize(value))
}
