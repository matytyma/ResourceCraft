package dev.matytyma.opencraft.version

data class ServerVersion(
    val major: Int,
    val minor: Int,
    val patch: Int = 0,
) : Comparable<ServerVersion> {
    val packFormat: PackFormat by lazy { PackFormat.forVersion(this) }

    companion object {
        fun fromVersionString(version: String): ServerVersion {
            val parts = runCatching {
                version.split('.').map(String::toInt)
            }.getOrElse { error("Could not parse version string '$version'") }
            require(parts.size in 2..3) { "Version must be either 'major.minor' or 'major.minor.patch'" }
            val major = parts[0]
            val minor = parts[1]
            val patch = parts.getOrNull(2) ?: 0
            return ServerVersion(major, minor, patch)
        }
    }

    override fun compareTo(other: ServerVersion) = when {
        major != other.major -> major.compareTo(other.major)
        minor != other.minor -> minor.compareTo(other.minor)
        patch != other.patch -> patch.compareTo(other.patch)
        else -> 0
    }

    operator fun rangeTo(other: ServerVersion) = ServerVersionRange(this, other)
}

class ServerVersionRange(
    override val start: ServerVersion,
    override val endInclusive: ServerVersion,
): ClosedRange<ServerVersion>
