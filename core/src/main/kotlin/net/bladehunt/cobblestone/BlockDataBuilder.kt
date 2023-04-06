package net.bladehunt.cobblestone

class BlockDataBuilder(
    private val x: Byte,
    private val y: Short,
    private val z: Byte,
    private val id: Short,
    private val lightLevel: Byte) {
    private val nbt = hashMapOf<String,Any>()
    fun setNBT(key: String, value: Any) {
        nbt[key] = value
    }
    fun getPosition(): BlockPosition {
        return BlockPosition(x,y.toInt(),z)
    }
    fun build(): BlockData {
        return BlockData(id,nbt,lightLevel)
    }
}