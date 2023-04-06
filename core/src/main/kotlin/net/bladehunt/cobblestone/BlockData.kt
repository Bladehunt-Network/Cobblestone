package net.bladehunt.cobblestone

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

data class BlockData(val id: Short, val nbt: Map<String,Any>, val lightLevel: Byte) {
    companion object {
        fun fromByteArray(byteArray: ByteArray): BlockData {
            val stream = DataInputStream(ByteArrayInputStream(byteArray))
            val id = stream.readShort()
            val lightLevel = stream.readByte()
            return BlockData(id, hashMapOf(), lightLevel)
        }
    }
    fun toByteArray(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val stream = DataOutputStream(byteStream)
        stream.writeShort(id.toInt())
        stream.writeByte(lightLevel.toInt())
        stream.close()
        return byteStream.toByteArray()
    }

    override fun toString(): String {
        return "BlockData(id=$id, nbt=$nbt, lightLevel=$lightLevel)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BlockData

        if (id != other.id) return false
        if (nbt != other.nbt) return false
        if (lightLevel != other.lightLevel) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nbt.hashCode()
        result = 31 * result + lightLevel
        return result
    }
}