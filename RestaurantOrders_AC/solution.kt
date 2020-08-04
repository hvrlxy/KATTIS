import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toIntArray() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numItems = rd.readInt()
    val a = rd.readInts()
    var max = 0

    rd.readInt()
    val queries = rd.readInts()

    for (i in queries){ if (i > max) max = i }

    val aMap = mutableMapOf<Int, MutableList<Int>>()

    val dp = Array<Array<Boolean?>>(numItems){Array<Boolean?>(max + 1){ false }}

    dp[0][a[0]] = true
    aMap[a[0]] = mutableListOf<Int>(1)
    aMap[0] = mutableListOf<Int>()

    for (j in 0 .. max){
    	if (j > a[0]){
    		dp[0][j] = dp[0][j - a[0]]
    		if (dp[0][j] == true){
	    		aMap[j] = mutableListOf<Int>()
	    		aMap[j]!!.addAll(aMap[j - a[0]]!!)
	    		aMap[j]!!.add(1)
    		}
    	}
    }

    for (i in 1 until numItems){
    	for (j in 0 .. max){
    		if (j == 0) dp[i][j] = true
    		else if (j >= a[i]){
    			if ((dp[i - 1][j - a[i]] == null || dp[i][j - a[i]] == null || dp[i - 1][j] == null) 
    				|| (dp[i][j - a[i]] == true && dp[i - 1][j] == true)
    				|| (dp[i - 1][j - a[i]] == true && dp[i - 1][j] == true))
    				dp[i][j] = null

    			else if (dp[i - 1][j - a[i]] == true || dp[i][j - a[i]] == true){
    				dp[i][j] = true
    				aMap[j] = mutableListOf<Int>()
    				aMap[j]!!.addAll(aMap[j - a[i]]!!)
    				aMap[j]!!.add(i + 1)
    			}
    			else if (dp[i - 1][j] == true) dp[i][j] = true
    		}
    		else{
    			dp[i][j] = dp[i - 1][j]
    		}
    	}
    }

    for (i in queries){
    	if (dp[(numItems - 1)][i] == false) println("Impossible")
    	else if (dp[(numItems - 1)][i] == null) println("Ambiguous")
    	else{
    		println(aMap[i]!!.joinToString(separator = " "))
    	}
    }
}