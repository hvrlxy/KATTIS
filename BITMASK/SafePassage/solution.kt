import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

val INF = 100000000

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val aline = rd.readInts()
    val numStudents = aline[0]

    val a = IntArray(numStudents){aline[it + 1]}

    /*
	
	There is two turns in the problem : going from the left side of the bridge to the right side
    and vice versa.

    Let d[m][i] represent the minimum time needed to get m people to the right side of the bridge,
    and i represent the turn of the puzzle.

    i = 0: send people from left to right
    i = 1: send people from right to left
    */

    val total = 1 shl numStudents
    val dp = Array<IntArray>(total){IntArray(2){INF}}

    dp[0][0] = 0 // the starting point of the puzzle

    for (m in 0 until total){
        for (j in 0 until 2){

            if (j == 0){
                // need to send ppl to the right side, so in the last turn, we only need to send
                // the fastest one
                var maxSpeed = INF
                var idx = -1

                for (i in 0 until numStudents){
                    if (m and (1 shl i) == 0 && a[i] < maxSpeed){
                        maxSpeed = a[i]
                        idx = i
                    }
                }

                if (idx >= 0) dp[m][j] = min(dp[m][j], dp[m or (1 shl idx)][1] + maxSpeed)
            }

            if (j == 1){
                // need to send ppl to the left side

                for (i in 0 until numStudents){
                    for (k in 0 until numStudents){

                        if (m and (1 shl i) != 0 && m and (1 shl k) != 0){  
                            dp[m][j] = min(dp[m][j], dp[m and ((1 shl i) and (1 shl k)).inv()][0] + max(a[i], a[k]))
                        }
                    }
                }
            }
        }
    }

    for (i in 0 until total){
        println("0".repeat(numStudents - i.toString(2).length) + i.toString(2)
            + ": " + dp[i].joinToString())
    }

    println(dp[total - 1][1])
}