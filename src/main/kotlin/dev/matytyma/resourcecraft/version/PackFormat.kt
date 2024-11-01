package dev.matytyma.resourcecraft.version

enum class PackFormat(
    val versionRange: ServerVersionRange,
    val value: Int,
) {
    ONE(ServerVersion(1, 6, 1)..ServerVersion(1, 8, 9), 1),
    TWO(ServerVersion(1, 9)..ServerVersion(1, 10, 2), 2),
    THREE(ServerVersion(1, 11)..ServerVersion(1, 12, 2), 3),
    FOUR(ServerVersion(1, 13)..ServerVersion(1, 14, 4), 4),
    FIVE(ServerVersion(1, 15)..ServerVersion(1, 16, 1), 5),
    SIX(ServerVersion(1, 16, 2)..ServerVersion(1, 16, 5), 6),
    SEVEN(ServerVersion(1, 17)..ServerVersion(1, 17, 1), 7),
    EIGHT(ServerVersion(1, 18)..ServerVersion(1, 18, 2), 8),
    NINE(ServerVersion(1, 19)..ServerVersion(1, 19, 2), 9),
    TWELVE(ServerVersion(1, 19, 3).let { it.rangeTo(it) }, 12),
    THIRTEEN(ServerVersion(1, 19, 4).let { it.rangeTo(it) }, 13),
    FIFTEEN(ServerVersion(1, 20)..ServerVersion(1, 20, 1), 15),
    EIGHTEEN(ServerVersion(1, 20, 2).let { it.rangeTo(it) }, 18),
    TWENTY_TWO(ServerVersion(1, 20, 3)..ServerVersion(1, 20, 4), 22),
    THIRTY_TWO(ServerVersion(1, 20, 5)..ServerVersion(1, 20, 6), 32),
    THIRTY_FOUR(ServerVersion(1, 21)..ServerVersion(1, 21, 1), 34),
    ;

    companion object {
        fun forVersion(version: ServerVersion) = entries.first { version in it.versionRange }
    }
}
