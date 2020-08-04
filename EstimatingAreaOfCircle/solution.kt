import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readDoubles() = readStrings().map { it.toDouble() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var aString = rd.readDoubles()
    while (aString[0] + aString[1] + aString[2] != 0.0){
    	val realArea = aString[0] * aString[0] * kotlin.math.PI
    	val estimateArea = aString[2] / aString[1] * (aString[0] * 2) * (aString[0] * 2)
    	println("$realArea $estimateArea")
    	aString = rd.readDoubles()
    }
}