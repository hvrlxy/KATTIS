import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()
	var caseNo = 0
	repeat(numCase){
		caseNo ++

		var S = rd.readLn()
		var T = rd.readLn()

		var result = 0

		if (S.count{ it == '1' } > T.count{ it == '1' }) println("Case $caseNo: -1")
		else{
			var diff = T.count{ it == '1' } - S.count{ it == '1' }
			var newS = ""
			for (c in 0 until T.length){
				if (S[c] == '?' && diff > 0 && T[c] == '1') {
					diff --
					newS += '1'
					result ++
				}
				else if (S[c] == '?'){
					result ++
					newS += '0'
				}
				else newS += S[c]
			}

			for (c in 0 until T.length){
				if (newS[c] == '0' && T[c] == '1') result ++
			}
			println("Case $caseNo: $result")
		}
	}
}