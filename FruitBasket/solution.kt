import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val num = rd.readInt()

    var fruits = rd.readInts()

    fun pow2 (e: Int): Long{
    	if (e == 0) return 1L
    	else if (e == 1) return 2L
    	else{
    		val half = pow2(e / 2)
    		if (e % 2 == 0) return half * half else return half * half * 2L
    	}
    }

    fun binaryString (n: Long): String{
    	val n1 = n.toString(2)
    	return ("${"0".repeat(num - n1.length)}$n1")
    }

    fun weight(s: String): Long{
    	var result = 0L
    	for (c in 0 until s.length){
    		if (s[c] == '1') result += fruits[c]
    	}
    	return result
    }

    var i = 0L
    val limit = pow2(num)

    var result = 0L
    while (i < limit - 1){
    	i++
    	val w = weight(binaryString(i))
    	if (w >= 200L) result += w
    }

    println(result)
}