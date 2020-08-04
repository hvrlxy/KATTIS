import java.io.*
import kotlin.math.*
import java.util.PriorityQueue

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val buildings = IntArray(s[1] + 1){0}
    var result = 0

    data class Noise (val building: Int, val numPeople: Int, val max: Int, val x: Int): Comparable<Noise>{
    	override fun compareTo(other: Noise): Int = other.max.compareTo(this.max)
    }

    repeat(s[0]){
    	val b = rd.readInt()

    	buildings[b] ++
    	result += buildings[b]
    }

    fun findMax(building: Int): Pair<Int, Int>{
    	var currentX = -1
    	var max = -1
    	var k = buildings[building]
    	for (x in 1 .. k){
    		val m = (x - 1) * (k - x + 1)
    		if (m > max){
    			max = m
    			currentX = x
    		}
    	}
    	return (max to currentX)
    }

    val Q = PriorityQueue<Noise>()
    for (i in 1 .. s[1]){
    	val p = findMax(i)
    	Q.add(Noise(i, buildings[i], p.first, p.second))
    }

    println(Q)

    repeat(s[2]){
    	val n = Q.poll()
    	println(n)

    	result -= n.max
    	buildings[n.building] -= (n.x - 1)
    	val p = findMax(n.building)

    	Q.add(Noise(n.building, buildings[n.building], p.first, p.second))
    }
    println(result)
}