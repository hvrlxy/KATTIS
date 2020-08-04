import java.math.BigInteger
import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))
	
	var aString = rd.readLn()
	val result = StringBuilder()

	val remain = (aString.length + 4) % 4

	for (i in aString.length downTo 0 step 4){
		if (i - 4 < 0) break
		val substring = aString.substring(i - 4, i).toInt(8).toString(16).toUpperCase()
		result.insert(0, substring)
		result.insert(0, "0".repeat(3 - substring.length))
	}
	if (remain > 0) result.insert(0, aString.substring(0, remain).toInt(8).toString(16).toUpperCase())

	println(result)
}