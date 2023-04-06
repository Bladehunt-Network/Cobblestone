import net.bladehunt.cobblestone.BlockData
import net.bladehunt.cobblestone.BlockPosition
import net.bladehunt.cobblestone.Chunk
import net.bladehunt.cobblestone.CobblestoneFile
import java.io.File

fun main() {
    val file = CobblestoneFile(File("test.cobblestone"))
    file.setChunk(Chunk(2,1, hashMapOf(
        Pair(BlockPosition(2,5,2),BlockData(200, hashMapOf(Pair("hi","hi")), 4)),
        Pair(BlockPosition(3,7,3),BlockData(133, hashMapOf(Pair("ha","hd")), 5)),
    )))
    file.write()

    file.read()
}