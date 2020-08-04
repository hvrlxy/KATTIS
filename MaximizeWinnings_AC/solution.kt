import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toIntArray() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var size = rd.readInt()
    while (size != 0){
    	val m = Array<IntArray>(size){rd.readInts()}
	    val turn = rd.readInt()

    	val max = Array<IntArray>(turn + 1){IntArray(size){Int.MIN_VALUE}}
    	val min = Array<IntArray>(turn + 1){IntArray(size){100000000}}

    	max[0][0] = 0
    	min[0][0] = 0

    	var minResult = 100000000
    	var maxResult = Int.MIN_VALUE

    	for (i in 1 .. turn){
    		for (j in 0 until size){
    			for (k in 0 until size){
    				max[i][j] = kotlin.math.max(max[i][j], max[i - 1][k] + m[k][j])
    				min[i][j] = kotlin.math.min(min[i][j], min[i - 1][k] + m[k][j])
    			}
    			if (i == turn){
    				minResult = kotlin.math.min(minResult, min[i][j])
    				maxResult = kotlin.math.max(maxResult, max[i][j])
    			}
    		}
    	}

    	println("$maxResult $minResult")
    	size = rd.readInt()
    }

}