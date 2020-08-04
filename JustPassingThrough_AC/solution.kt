import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

val INF = 100000000

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val r = s[0]
    val c = s[1]
    val n = s[2]

    val table = Array<IntArray>(r){IntArray(c){0}}

    // read in the table

    for (i in 0 until r){
    	val aline = rd.readInts()
    	for (j in 0 until c){
    		table[i][j] = aline[j]
    	}
    }

    // a function to test if a square if a pass or not

    fun isPass(x: Int, y: Int): Boolean{
    	if (x == 0 || x == r - 1 || y == 0 || y == c - 1) return false // lie in the edge

    	if (table[x][y] == -1 || table[x][y - 1] == -1 || table[x][y + 1] == -1
    		|| table[x - 1][y] == -1 || table[x + 1][y] == -1) return false

    	return table[x][y] < table[x + 1][y] && table[x][y] < table[x - 1][y] &&
    	table[x][y] > table[x][y - 1] && table[x][y] > table[x][y + 1]
    }

    //create a look up table
    val passTable = Array<BooleanArray>(r){BooleanArray(c)}

    for (i in 0 until r){
    	for (j in 0 until c){
    		passTable[i][j] = isPass(i,j)
    	}
    }

    val dp = Array<Array<IntArray>>(r){Array<IntArray>(c){IntArray(n + 1){INF}}}

    //base-case
    for (i in 0 until r){
    	if (passTable[i][0]){
    		dp[i][0][1] = table[i][0]
    	}
    	else{
    		dp[i][0][0] = table[i][0]
    	}
    }

    for (j in 1 until c){
    	for (i in 0 until r){
    		for (k in 0 .. n){
    			//println("$i $j $k")
    			if (table[i][j] == -1) continue
    			if (!passTable[i][j]){
    				//if the current square is not a "pass"
    				if (table[i][j - 1] != -1) {
    					//next to
    					//println("table[$i][${j - 1}][$k]: ${dp[i][j - 1][k]}")
    					dp[i][j][k] = min(dp[i][j][k], dp[i][j - 1][k] + table[i][j])
    				}
    				if (i > 0 && table[i - 1][j - 1] != -1){
    					dp[i][j][k] = min(dp[i][j][k], dp[i - 1][j - 1][k] + table[i][j])
    				}
    				if (i < r - 1 && table[i + 1][j - 1] != -1){
    					dp[i][j][k] = min(dp[i][j][k], dp[i + 1][j - 1][k] + table[i][j])
    				}

    			}
    			else{
    				//if the current square is a "pass"

    				if (table[i][j - 1] != -1 && k > 0) {
    					//next to
    					dp[i][j][k] = min(dp[i][j][k], dp[i][j - 1][k - 1] + table[i][j])
    				}

    				if (i > 0 && table[i - 1][j - 1] != -1 && k > 0){
    					dp[i][j][k] = min(dp[i][j][k], dp[i - 1][j - 1][k - 1] + table[i][j])
    				}

    				if (i < r - 1 && table[i + 1][j - 1] != -1 && k > 0){
    					dp[i][j][k] = min(dp[i][j][k], dp[i + 1][j - 1][k - 1] + table[i][j])
    				}

    			}
    			//println("$i $j $k : ${dp[i][j][k]} ; table: ${table[i][j]}")
    		}
    	}
    }

    var min = INF
    for (i in 0 until r){
    	if (dp[i][c - 1][n] < min) min = dp[i][c - 1][n]
    }
    if (min == INF) return println("impossible")
    println(min)
}