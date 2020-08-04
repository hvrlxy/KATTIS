import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val MOD = 998244353

    val s = rd.readInts()
    val m = s[0]
    val k = s[1]

    val dp = IntArray(k + 1){0}

    fun power(k: Int): Int{
    	if (k == 0) return 1
    	val half = power(k / 2) % MOD
    	if (k % 2 == 0) return half * half % MOD else return half * half % MOD * 3 % MOD
    }

    dp[1] = power(m)

    for (i in 2 .. k){
    	dp[i] = dp[i - 1] * i - 1
    }

    println(dp[k])
}