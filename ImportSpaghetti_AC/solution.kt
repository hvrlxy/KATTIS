import java.io.*
import kotlin.math.*
import java.util.LinkedList
import java.util.Queue
import java.util.ArrayDeque

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val N = rd.readInt()

    val files = rd.readStrings()

    val fileName = mutableMapOf<String, Int>()

    var count = 0
    for (f in files){
    	fileName[f] = count
    	count ++
    }

    val graph = Array<MutableList<Int>>(N){mutableListOf<Int>()}

    repeat(N){
    	val a = rd.readStrings()

    	repeat(a[1].toInt()){
    		val s = rd.readLn().split("import ")

    		val s1 = s[1].split(", ")

    		for (f in s1){
		    	graph[fileName[a[0]]!!].add(fileName[f]!!)
    		}
    	}
    }

    var parentArray = IntArray(N){-1}
    fun detectCycle(graph: Array<MutableList<Int>>, v: Int): Boolean{
		parentArray = IntArray(graph.size){-1}
		val d = BooleanArray(graph.size)

		val Q : Queue<Int> = LinkedList<Int>()
		Q.add(v)
		while (Q.isNotEmpty()){

			val w = Q.poll()
			for (u in graph[w]){
				if (u == v) {
					parentArray[v] = w
					return true
				}
				if (!d[u]){
					d[u] = true
					Q.add(u)
					parentArray[u] = w
				}
			}
		}
		
		return false
	}

	fun cycle(parentArray: IntArray, v: Int): MutableList<Int>{
		val alist = ArrayDeque<Int>()

		var currentVertex = v
		alist.add(currentVertex)
		while (parentArray[currentVertex] != v){
			currentVertex = parentArray[currentVertex]
			alist.addFirst(currentVertex)
		}

		return alist.toMutableList()
	}

	var result = mutableListOf<Int>()

	for (i in 0 until N){
		if (detectCycle(graph, i)){
			val cycle = cycle(parentArray, i)

			if (result.isEmpty() || cycle.size < result.size){
				result = cycle
			}
		}
	}

	if (result.isEmpty()) return println("SHIP IT")

	println(result.map{files[it]}.joinToString(separator = " "))
}