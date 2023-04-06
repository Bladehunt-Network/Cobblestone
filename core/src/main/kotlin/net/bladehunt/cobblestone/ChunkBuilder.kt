package net.bladehunt.cobblestone

class ChunkBuilder(private val x: Int, private val z: Int) {
    private val blocks = hashMapOf<BlockPosition,BlockData>()
    fun setBlock(pos: BlockPosition, data: BlockData) {
        blocks[pos] = data
    }
    fun build(): Chunk {
        return Chunk(x,z,blocks.toMap())
    }
}