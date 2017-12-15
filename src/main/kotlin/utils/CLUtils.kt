package utils
import data.PokerHand
import java.io.File
import java.io.InputStream

fun readFileWithPath(filePath:String)
{
    val rest:MutableList<Pair<PokerHand,PokerHand>> = parseFileWithPath(filePath)
}
fun parseFileWithPath(filePath:String): MutableList<Pair<PokerHand,PokerHand>>
{
    var pokerHands:MutableList<Pair<PokerHand,PokerHand>> = ArrayList()

    val file = File(filePath)
    file.forEachLine {
        pokerHands.add(parseLine(it))
    }

    return pokerHands
}

private fun parseLine(line:String):Pair<PokerHand,PokerHand>
{
    val separate1 = line.split(" ".toRegex())
    return Pair(PokerHand(separate1.take(5).joinToString(" ")), PokerHand(separate1.takeLast(5).joinToString(" ")))
}