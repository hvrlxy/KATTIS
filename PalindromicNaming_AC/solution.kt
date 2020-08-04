import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val MOD = 1000000007

    val numStrings = rd.readInt()

    repeat(numStrings){
    	val s = rd.readLn()

    	val dp = Array<IntArray>(s.length){IntArray(s.length){0}}

    	for (i in 0 until s.length){
    		dp[i][i] = 1
    	}

    	for (k in 2 .. s.length){
    		for (i in 0 until s.length){
    			val j = i + k - 1
    			if (j < s.length){
	    			if (s[i] == s[j]){
	    				dp[i][j] = ((1 + dp[i + 1][j]) % MOD + dp[i][j - 1]) % MOD
	    			}
	    			else{
	    				dp[i][j] = (((dp[i + 1][j] + dp[i][j - 1]) % MOD - dp[i + 1][j - 1]) % MOD + MOD) % MOD
	    			}
	    		}
    		}
    	}

    	println(dp[0][s.length - 1] % MOD)
    }
}