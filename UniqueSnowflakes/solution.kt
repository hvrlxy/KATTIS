import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()
    repeat(numCase){
        val numSnow = rd.readInt()
        val a = IntArray(numSnow){rd.readInt()}

        val aSet = mutableSetOf<Int>()
        var result = 0

        for (i in numSnow - 1 downTo 0){
        	if (a[i] !in aSet){
        		result++
        		aSet.add(a[i])
        	}
        }
        println(result)
    }


}