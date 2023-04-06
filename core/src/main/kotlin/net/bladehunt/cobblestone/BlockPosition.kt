package net.bladehunt.cobblestone

data class BlockPosition(val x: Byte, val y: Int, val z: Byte) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BlockPosition

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.toInt()
        result = 31 * result + y
        result = 31 * result + z
        return result
    }

    override fun toString(): String {
        return "BlockPosition(x=$x, y=$y, z=$z)"
    }
}