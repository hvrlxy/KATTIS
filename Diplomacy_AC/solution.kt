import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

/*
	The key is that an optimal solution can be achieved by tossing just one person back and forth
*/

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val n = s[0] // num governors

    val m = s[1] // num friendships

    val parties = rd.readInts()

    val ORIGRAPH = Array<MutableList<Int>>(n + 1){mutableListOf<Int>()}

    repeat(m){
    	val a = rd.readInts()
    	ORIGRAPH[a[0]].add(a[1])
    	ORIGRAPH[a[1]].add(a[0])
    }

    fun tryPerson(v: Int, p: MutableList<Int>): Int {
    	var parties = IntArray(n){0}
    	for (i in 0 until n){
    		parties[i] = p[i]
    	}
    	var count = 0

	    fun tossing (v: Int, c: Int){

	    	parties[v - 1] = 1 - parties[v - 1]
	    	for (u in ORIGRAPH[v]){
	    		//println("$u ${parties[u]} $c")
	    		if (parties[u - 1] == c){
	    			tossing(u, c)
	    		}
	    	}
	    }

	    fun isDone(parties: IntArray): Boolean = (parties.count{it == 0} == 0) || (parties.count{it == 0} == n)

	    while (!isDone(parties)){
	    	count ++
	    	//println(parties.joinToString())
	    	tossing(v, parties[v - 1])
	    }
	    //println(p.joinToString())

	    return count
	}

    var count = Int.MAX_VALUE
    for (i in 1 .. n){
    	val c = tryPerson(i, parties)
    	if (c < count) count = c
    }

    println(count)

    
}