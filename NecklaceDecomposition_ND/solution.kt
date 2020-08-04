import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toList() // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()
	var resultString = StringBuilder()
	repeat(numCase){
		val aString = rd.readLn()

		var startIdx = 0
		while (startIdx < aString.length){
			var currentIdx = startIdx
			var numZero = 0
			for (i in startIdx until aString.length){
				if (aString[i] == '0') numZero ++
				if (aString[i] == '1') {
					currentIdx = i
					break
				}
				if (i == aString.length - 1) currentIdx = i + 1
			}
			var numOnes = 0
			for (i in currentIdx until aString.length){
				if (aString[i] == '1') numOnes ++
				else {
					currentIdx = i
					break
				}
				if (i == aString.length - 1) {
					currentIdx = i + 1
				}
			}

			var rstring = "${"0".repeat(numZero)}${"1".repeat(numOnes)}"

			fun nextZeros (s: Int): Int{
				var result = 0
				for (i in s until aString.length){
					if (aString[i] == '0') result ++ else return result
				}
				return result
			}

			fun nextOnes (s: Int): Int{
				var result = 0
				for (i in s until aString.length){
					if (aString[i] == '1') result ++ else return result
				}
				return result
			}

			if (numOnes == 0) currentIdx ++

			while (currentIdx < aString.length && numOnes != 0){
				val n0 = nextZeros(currentIdx)
				if (n0 > numZero) break
				else if (n0 == numZero){
					val n1 = nextOnes(currentIdx + n0)
					if (n1 == 0) break
					if (n1 < numOnes) {
						break
					}
					else {
						rstring += "${"0".repeat(n0)}${"1".repeat(n1)}"
						currentIdx = currentIdx + n0 + n1
					}
				}
				else{
					val n1 = nextOnes(currentIdx + n0)
					if (n1 == 0) {
						break
					}
					//println("$currentIdx $rstring $n0 $n1")
					rstring += "${"0".repeat(n0)}${"1".repeat(n1)}"
					currentIdx += (n0 + n1)
				}
			}
			startIdx = currentIdx
			resultString.append("($rstring)")
		}
		resultString.append("\n")
	}
	print(resultString)
}