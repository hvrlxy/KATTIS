import java.io.*
import kotlin.math.min

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

var numComponents = 1

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val n = s[0] //num towns
    val m = s[1] //num roads

    val graph = Array<MutableList<Int>>(n){mutableListOf<Int>()}

    repeat(m){
    	val a = rd.readInts()
    	graph[a[0]].add(a[1])
    	//graph[a[1]].add(a[0])
    }

    println(scc(graph).joinToString())
}

fun printGraph(graph: Array<MutableList<Int>>){
	for (i in 0 until graph.size){
		println(graph[i].joinToString())
	}
}

fun scc(graph: Array<MutableList<Int>>): IntArray{
	// finding scc on a directed graph
	val indices = IntArray(graph.size)  {-1}
    var stack = mutableListOf<Int>()
    var c = graph.size


    // create another parameter u which is the parent of the vertex v
    fun dfs(v: Int){
        stack.add(v)
        indices[v] = stack.size - 1
        for (w in graph[v]){
            if (indices[w] == -1){
                dfs(w)
            }
            indices[v] = min(indices[w], indices[v])
            break
        }

        if (stack[indices[v]] == v){
            c ++
            numComponents ++
            while (indices[v] < stack.size){
                indices[stack[stack.size - 1]] = c
                stack.removeAt(stack.size - 1)
            }
        }
    }

    for (i in 0 until graph.size){
        if (indices[i] == -1) {
            dfs(i)
        }
    }

    return indices.map{it - numComponents}.toIntArray()
}