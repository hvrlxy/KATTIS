import java.io.*
import kotlin.math.min

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))
    var aline = rd.readLn()
    var caseNo = 0

    while (aline != null){
    	caseNo ++
    	val a = aline.split(" ").map{it.toInt()}

    	val originalGraph = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}
    	repeat(a[1]){
    		val s = rd.readInts()
    		originalGraph[s[0]].add(s[1])
    	}

    	val indices = IntArray(originalGraph.size)  {-1}
	    var stack = mutableListOf<Int>()
	    var c = originalGraph.size


	    // create another parameter u which is the parent of the vertex v
	    fun dfs(v: Int){
	        stack.add(v)
	        indices[v] = stack.size - 1
	        for (w in originalGraph[v]){
	            if (indices[w] == -1){
	                dfs(w)
	            }
	            indices[v] = min(indices[w], indices[v])
	            break
	        }

	        //println(stack)

	        if (stack[indices[v]] == v){
	            c ++
	            while (indices[v] < stack.size){
	                indices[stack[stack.size - 1]] = c
	                stack.removeAt(stack.size - 1)
	            }
	        }
	    }

	    for (i in 0 until originalGraph.size){
	        if (indices[i] == -1) {
	            dfs(i)
	        }
	    }

	    fun isValid(): Boolean{
	    	for (i in 0 until indices.size - 1){
	    		if (indices[i] != indices[i + 1]) return false
	    	}
	    	return true
	    }

	    if (isValid()) println("Case $caseNo: valid")

	    else{
	    	val newGraph = Array<MutableSet<Int>>(c - a[0]){mutableSetOf<Int>()}
	        val indegreeArray = IntArray(c - a[0]){0}

	        for (i in 0 until indices.size){
	            for (j in originalGraph[i]){
	                newGraph[indices[i] - originalGraph.size - 1].add(indices[j] - originalGraph.size - 1)
	                indegreeArray[indices[j] - originalGraph.size - 1] ++
	            }
	        }

	        fun reverseEdges(): Pair<Int, Int>{
	        	var sink = -1
		        for (i in 0 until newGraph.size){
		        	//finding the sink of the graph, if there is more than 1 sink, then the graph is invalid
		            if (newGraph[i].isEmpty() && sink == -1) sink = i
		            else if (newGraph[i].isEmpty()) return (-1 to -1)
		        }

		        var source = -1
		        for (i in 0 until indegreeArray.size){
		        	//finding the source of the graph, if there s more than one source, then the graph is invalid
		        	if (indegreeArray[i] == 0 && source == -1) source = i
		        	else if (indegreeArray[i] == 0) return (-1 to -1)
		        }

		        //println("$source $sink")
		        if (sink == -1 || source == -1) return (-1 to -1)
		        if (sink !in newGraph[source]) return (-1 to -1) // is the sink is not connected to the source then the graph is invalid
		        else{
		        	// search for the edge in the original graph that connects the source to the sink in the contracted graph
		        	for (i in 0 until indices.size){
		        		if (indices[i] == source + originalGraph.size + 1){
		        			for (j in originalGraph[i]){
		        				if (indices[j] - originalGraph.size - 1 == sink) return (i to j)
		        			}
		        		}
		        	}
		        }
		        return (-1 to -1)
	        }

	        val resultEdge = reverseEdges()
	        if (resultEdge == (-1 to -1)) println("Case $caseNo: invalid") else println("Case $caseNo: ${resultEdge.first} ${resultEdge.second}")
	    }
	    aline = rd.readLn()
    }

}