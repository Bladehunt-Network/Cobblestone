package net.bladehunt.cobblestone

import net.minestom.server.instance.Chunk
import net.minestom.server.instance.DynamicChunk
import net.minestom.server.instance.IChunkLoader
import net.minestom.server.instance.Instance
import net.minestom.server.instance.block.Block
import java.io.File
import java.util.concurrent.CompletableFuture

class CobblestoneLoader(file: File) : IChunkLoader {
    private val cobblestoneFile = CobblestoneFile(file)
    override fun loadChunk(instance: Instance, chunkX: Int, chunkZ: Int): CompletableFuture<Chunk?> {
        val chunk = DynamicChunk(instance,chunkX,chunkZ)
        for (block in cobblestoneFile.getChunk(chunkX, chunkZ).blocks) {
            chunk.setBlock(block.key.x.toInt(),block.key.y,block.key.z.toInt(), Block.fromBlockId(block.value.id.toInt())!!.withProperty())
        }
    }

    override fun saveChunk(chunk: Chunk): CompletableFuture<Void> {
        TODO("Not yet implemented")
    }
}