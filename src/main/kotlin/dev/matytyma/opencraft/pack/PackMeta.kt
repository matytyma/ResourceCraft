package dev.matytyma.opencraft.pack

import dev.matytyma.opencraft.serializer.PackFormatRangeSerializer
import dev.matytyma.opencraft.serializer.PackFormatSerializer
import dev.matytyma.opencraft.version.PackFormat
import kotlinx.serialization.*

@Serializable
class PackMeta(val pack: Pack)

@Serializable
class Pack(
    val description: String,
    @SerialName("pack_format")
    @Serializable(PackFormatSerializer::class)
    val packFormat: PackFormat,
    @SerialName("supported_formats")
    @Serializable(PackFormatRangeSerializer::class)
    val supportedFormats: ClosedRange<PackFormat>,
)

fun PackMeta(
    description: String = "",
    packFormat: PackFormat,
    supportedFormats: ClosedRange<PackFormat> = packFormat..packFormat,
): PackMeta {
    require(packFormat in supportedFormats) { "The range has to include the value of packFormat" }
    return PackMeta(Pack(description, packFormat, supportedFormats))
}
