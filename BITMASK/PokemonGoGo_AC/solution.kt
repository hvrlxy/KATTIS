import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

data class Point(val x: Int, val y: Int)

fun manhattanDist(a: Point, b: Point): Int = abs(a.x - b.x) + abs(a.y - b.y)
val INF = 2000000000
val dp = Array<Array<IntArray>>(1 shl 16){Array<IntArray>(21){IntArray(21){INF}}}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numStops = rd.readInt()

    val aMap = mutableMapOf<Int, MutableList<Int>>()
    aMap[0] = mutableListOf(0) // the starting point is considered as a pokemon type

    val pokemonMap = mutableMapOf<String, Int>()

    val pList = mutableListOf(Point(0,0))

    var pokemon = 1
    repeat(numStops){
    	val aline = rd.readStrings()

    	pList.add(Point(aline[0].toInt(), aline[1].toInt()))
    	if (aline[2] !in pokemonMap.keys){
    		pokemonMap[aline[2]] = pokemon
    		aMap[pokemon] = mutableListOf(pList.size - 1)
    		
    		pokemon ++
    	}
    	else{
    		aMap[pokemonMap[aline[2]]]!!.add(pList.size - 1)
    	}
    }

    val total = 1 shl (pokemon)
    //println((total - 1).toString(2))
    dp[0][0][0] = 0

    for (m in 0 until total){
    	for (j in 0 until pokemon){
    		val alist = aMap[j]!!
    		for (i in alist){

    			for (k in 0 until pokemon){
    				if (m and (1 shl k) != 0){
    					val blist = aMap[k]!!
    					for (p in blist){ //index of the pokemon
    						dp[m][j][i] = min(dp[m][j][i], 
    							dp[m and (1 shl k).inv()][k][p] + manhattanDist(pList[p], pList[i]))
    					}
    				}
    			}

    		}
    	}
    }

    println(dp[total - 1][0][0])
}


