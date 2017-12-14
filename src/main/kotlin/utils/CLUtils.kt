package utils
import java.io.File
import java.io.InputStream

fun readFileWithPath(filePath:String)
{
    val rest:MutableList<Pair<String,String>> = parseFileWithPath(filePath)
}
fun parseFileWithPath(filePath:String): MutableList<Pair<String,String>>
{
    val inputStream: InputStream = File(filePath).inputStream()
    val lineList = mutableListOf<String>()

    var pokerHands:MutableList<Pair<String,String>> = ArrayList()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
    lineList.forEach{println(">  " + it)}

    val file = File(filePath)
    file.forEachLine {
        println(it)
        pokerHands.add(parseLine(it))
    }

    return pokerHands
}

private fun parseLine(line:String):Pair<String,String>
{
    val separate1 = line.split(" ".toRegex())
    println(separate1)

    return Pair(separate1.take(5).joinToString(" "), separate1.takeLast(5).joinToString(" "))
}