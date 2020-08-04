import java.io.*
import kotlin.math.min

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val d = s[0]
    val t = s[1]

    val dp = Array<DoubleArray>(d + 1){DoubleArray(t + 1){Double.MAX_VALUE}} //beware of the upperbound
    val rain = DoubleArray(t + 1){0.0}
    repeat(s[2]){
    	val aline = rd.readStrings()

    	val p = aline[2].toDouble()
    	val a = aline[3].toDouble()

    	rain[aline[0].toInt()] += p * a
    	rain[aline[1].toInt()] -= p * a
    }
    /*
    	using prefix sum to calculate the change in rain and do not have to loop
    	a lot of time
    */
    for (i in 1 until rain.size){
    	rain[i] += rain[i - 1]
    }

    val isRoof = BooleanArray(d + 2){false}
    repeat(s[3]){
    	val roof = rd.readInts()
    	for (i in roof[0] until roof[1]){
    		isRoof[i] = true
    	}
    }

    for (i in 0 .. t){
    	dp[0][i] = 0.0
    }

    for (i in 1 .. d){
    	for (j in i .. t){
    		if (!isRoof[i] && !isRoof[i - 1]) dp[i][j] = min(dp[i][j], dp[i][j - 1] + rain[j - 1])
    		else dp[i][j] = min(dp[i][j], dp[i][j - 1])


    		if (isRoof[i - 1]){
    			dp[i][j] = min(dp[i][j], dp[i - 1][j - 1])
    		}
    		else{
    			dp[i][j] = min(dp[i][j], dp[i - 1][j - 1] + rain[j - 1])
    		}
    	}
    }

    println("%.7f".format(dp[d][t]))
}


