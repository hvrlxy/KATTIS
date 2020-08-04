import java.io.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var aline = rd.readInts()
	while (aline[0] != 0){
		var w = 0
		val a = mutableListOf<Int>()
		for (i in 1 .. aline[0]){
			a.add(aline[i])
			w += aline[i]
		}
		val totalW = w
		w /= 2

		val dp = Array<IntArray>(aline[0]){IntArray(w + 1){0}}

		// let dp[i][j] be the sum of the max subset from [0..i] has sum smaller or equal to j
		// dp[i][j] = max(dp[i - 1][j], a[i] + dp[i - 1][j - a[i]]

		for (i in 0 until aline[0]){
			for (j in 0 until w + 1){
				if (j == a[i]) dp[i][j] = j
				else if (i == 0){
					if (j >= a[i]) dp[i][j] = a[i]
				}
				else{
					dp[i][j] = kotlin.math.max(dp[i][j], dp[i - 1][j])
					if (j >= a[i]){
						dp[i][j] = kotlin.math.max(dp[i][j], a[i] + dp[i - 1][j - a[i]])
					}
				}
			}
		}
		println("${totalW - dp[aline[0] - 1][w]} ${dp[aline[0] - 1][w]}")

		aline = rd.readInts()
	}
}