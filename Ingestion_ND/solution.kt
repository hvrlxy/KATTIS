import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()
    val a = rd.readInts()
    val dp = Array<IntArray>(s[0] + 1){IntArray(s[1] + 1){0}}

    var m = s[1]

    var result = Int.MIN_VALUE

    for (i in 1 .. s[0]){
    	for (k in 0 .. m){
    		if (i == 1) dp[i][k] = min(a[i - 1], k)
    		else{
    			var j = (k * 3 / 2)
    			var k0 = k
    			while (j <= m){
    				dp[i][k] = max(dp[i - 1][j] + min(k, a[i - 1]), dp[i][k])
    				if (j < m && (j + 1) * 2 / 3 == k0 && dp[i - 1][j + 1] + min(k, a[i - 1]) > dp[i][k]){
    					j ++
    					dp[i][k] = dp[i - 1][j] + min(k, a[i - 1])
    				}
    				else if (j > 0 && (j - 1) * 2 / 3 == k0 && dp[i - 1][j - 1] + min(k, a[i - 1]) > dp[i][k]){
    					j --
    					dp[i][k] = dp[i - 1][j] + min(k, a[i - 1])
    				}
    				k0 = j
    				j = j * 3 / 2
    				if (j == 0) break
    			}
    		}
    	}
    }

    while (m > 0){
    	if (dp[s[0]][m] > result) result = dp[s[0]][m]

    	println("${m} ${dp[s[0]][m]}")
    	m = m * 2 / 3
    }

    println(result)
}