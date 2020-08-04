import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a = rd.readInts()
    val P = a[0]
    val T = a[1]

    var result = 0
    repeat(P){
    	var isSolved = true
    	repeat(T){
    		val aString = rd.readLn()

    		val bString = aString.replaceFirst(aString[0], aString[0].toLowerCase())
    		//println(bString)

    		if (bString != bString.toLowerCase()) isSolved = false
    	}
    	if (isSolved) result ++
    }
    println(result)
}