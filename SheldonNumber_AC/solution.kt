import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong() } // list of ints

val INF = 100000000

fun generateSheldon(limit: Int): MutableList<Long>{
	val result = mutableListOf<Long>()
	for (A in 1 .. limit){
		for (B in 0 .. limit - A){

			var s = ""
			var isA = true

			while (s.length <= limit){
				//println("$A $B")
				if (isA){
					s += "1".repeat(A)
					if (s.length > limit) break
					result.add(s.toLong(2))
					isA = !isA
				}
				else{
					s += "0".repeat(B)
					if (s.length > limit) break
					result.add(s.toLong(2))
					isA = !isA
				}
			}
		}
	}
	return result
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val sheldonList = mutableSetOf<Long>()
    for (i in 1 until 64){
    	sheldonList.addAll(generateSheldon(i))
    }

    val sorted = sheldonList.sorted()
    val range = rd.readInts()


    var count = 0
    for (n in 0 until sorted.size){
    	if (sorted[n] >= range[0] && sorted[n] <= range[1]) count++
    	else if (sorted[n] > range[1]) break
    }

    println(count)
}