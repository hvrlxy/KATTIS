import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()
    val h0 = s[0]
    val w0 = s[1]
    val h = s[2]
    val w = s[3]

    val p0 = Array<BooleanArray>(h0){BooleanArray(w0)}
    val p = Array<BooleanArray>(h){BooleanArray(w)}

    fun isValid(hs: Int, ws: Int): Boolean{
    	for (i in 0 until h0){
    		for (j in 0 until w0){
    			if (p0[i][j] != p[hs + i][ws + j]) return false
    		}
    	}
    	return true
    }

    for (i in 0 until h0){
    	val aline = rd.readLn()
    	for (j in 0 until w0){
    		if (aline[j] == 'o') p0[i][j] = true
    	}
    }

    for (i in 0 until h){
    	val aline = rd.readLn()
    	for (j in 0 until w){
    		if (aline[j] == 'o') p[i][j] = true
    	}
    }

    var count = 0
    for (i in 0 until h - h0 + 1){
    	for (j in 0 until w - w0 + 1){
    		if (isValid(i, j)) count ++
    	}
    }

    println(count)
}