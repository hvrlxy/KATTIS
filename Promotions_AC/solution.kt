import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val A = s[0]	//interval [A .. B]
    val B = s[1]

    val E = s[2]	//number of employees
    val P = s[3]	//number f precedences

    val indegreeArray = IntArray(E){0}

    val graph = Array<MutableList<Int>>(E){mutableListOf<Int>()}
    val rGraph = Array<MutableList<Int>>(E){mutableListOf<Int>()}

    repeat(P){
    	val aline = rd.readInts()

    	graph[aline[0]].add(aline[1])
    	rGraph[aline[1]].add(aline[0])
    }

    val Pred = IntArray(E){findSize(rGraph, it)}
    val Succ = IntArray(E){findSize(graph, it)}

    var countA = 0
    var countB = 0
    var countI = 0

    for (i in 0 until E){
    	if (Pred[i] > B) countI++
    	if (Succ[i] > E - B) countB ++
    	if (Succ[i] > E - A) countA ++
    }

    println("$countA\n$countB\n$countI")
}

fun findSize(graph: Array<MutableList<Int>>, v: Int): Int{
	var d = BooleanArray(graph.size)

	var count = 0

	fun dfs(u: Int){
		if (d[u]) return else d[u] = true
		count++
		for (i in graph[u]){
			dfs(i)
		}
	}

	dfs(v)

	return count
}
