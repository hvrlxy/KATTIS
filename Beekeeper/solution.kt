import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int

val doubleVowel = setOf("aa", "uu", "ee", "oo", "ii", "yy")

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var numWords = rd.readInt()
	while (numWords != 0){
		var maxWords = ""
		var max = -1
		repeat(numWords){
			val aString = rd.readLn()
			var count = 0
			for (i in 0 until aString.length - 1){
				val word = aString[i].toString() + aString[i + 1]
				if (word in doubleVowel) count ++
			}
			if (count > max){
				max = count
				maxWords = aString
			}
		}
		println(maxWords)
		numWords = rd.readInt()
	}
}