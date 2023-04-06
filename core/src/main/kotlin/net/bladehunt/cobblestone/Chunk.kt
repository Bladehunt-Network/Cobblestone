package net.bladehunt.cobblestone

class Chunk(val x: Int, val z: Int, val blocks: Map<BlockPosition,BlockData>) {
    /**
     * Returns null if block is air.
     */
    fun getBlock(blockPosition: BlockPosition): BlockData? {
        return blocks[blockPosition]
    }
    fun isEmpty(): Boolean {
        return blocks.isEmpty()
    }

    override fun toString(): String {
        return "Chunk(x=$x, z=$z, blocks=$blocks)"
    }
}