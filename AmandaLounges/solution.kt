import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    data class Edge (val u: Int, val v: Int, val w: Int)

    val s = rd.readInts()
    val bArray = BooleanArray(s[0] + 1){false}

    val edgeArray = mutableListOf<Edge>()

    repeat(s[1]){
    	val aline = rd.readInts()

    	edgeArray.add(Edge(aline[0], aline[1], aline[2]))
    }
}