package net.bladehunt.cobblestone

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.EOFException
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.concurrent.ConcurrentHashMap
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

class CobblestoneFile(val file: File) {
    companion object {
        private const val chunkOpening = 0
        private const val chunkClosing = 1

        private const val blockDataOpening = 2
        private const val blockDataClosing = 3
    }
    private val chunks = ConcurrentHashMap<Pair<Int,Int>,Chunk>()
    fun read() {
        val stream = DataInputStream(GZIPInputStream(FileInputStream(file)))
        var data = stream.readInt()
        var chunkBuilder: ChunkBuilder? = null
        var blockBuilder: BlockDataBuilder? = null
        while (data != -1) {
            when (data) {
                chunkOpening -> {
                    chunkBuilder = ChunkBuilder(stream.readInt(),stream.readInt())
                }
                chunkClosing -> {
                    println("Closing Chunk")
                }
                blockDataOpening -> {
                    if (chunkBuilder == null) throw ChunkNotBeingBuiltException("This shouldn't happen.")
                    blockBuilder = BlockDataBuilder(stream.readByte(),stream.readShort(),stream.readByte(),stream.readShort(),stream.readByte())
                }
                blockDataClosing -> {
                    if (chunkBuilder == null || blockBuilder == null) throw ChunkNotBeingBuiltException("This shouldn't happen.")
                    chunkBuilder.setBlock(blockBuilder.getPosition(),blockBuilder.build())
                }
            }
            try {
                data = stream.readInt()
            } catch (e: EOFException) {
                println(chunkBuilder?.build())
                println("End of file.")
                stream.close()
                break
            }
        }
    }
    fun write() {
        val stream = DataOutputStream(GZIPOutputStream(FileOutputStream(file,false)))
        for (chunk in chunks.values) {
            if (chunk.isEmpty()) continue
            stream.writeInt(chunkOpening)
            stream.writeInt(chunk.x)
            stream.writeInt(chunk.z)
            for (block in chunk.blocks) {
                stream.writeInt(blockDataOpening)
                stream.writeByte(block.key.x.toInt())
                stream.writeShort(block.key.y)
                stream.writeByte(block.key.z.toInt())
                stream.writeShort(block.value.id.toInt())
                stream.writeByte(block.value.lightLevel.toInt())
                stream.writeInt(blockDataClosing)
            }
            stream.writeInt(chunkClosing)
        }
        stream.close()
    }
    fun getChunk(x: Int, z: Int): Chunk {
        return chunks.getOrDefault(Pair(x,z),Chunk(x,z,hashMapOf()))
    }
    fun setChunk(chunk: Chunk) {
        chunks[Pair(chunk.x,chunk.z)] = chunk
    }
    fun save() {

    }
}