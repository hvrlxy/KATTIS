import java.io.*
import kotlin.math.min

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()
    var print = StringBuilder()
    repeat(numCase){
    	val a = rd.readInts()

    	val originalGraph = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}
    	repeat(a[1]){
    		val s = rd.readInts()
    		originalGraph[s[0] - 1].add(s[1] - 1)
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

	    //var result = 0

	    for (i in 0 until originalGraph.size){
	        if (indices[i] == -1) {
	        	//result ++
	            dfs(i)
	        }
	    }
        val isSourceArray = BooleanArray(c - a[0]){true}
        var result = c - a[0]

        for (i in 0 until indices.size){
            for (j in originalGraph[i]){
            	if (indices[i] - originalGraph.size - 1 != indices[j] - originalGraph.size - 1){
            		if (isSourceArray[indices[j] - originalGraph.size - 1]) {
            			result --
                		isSourceArray[indices[j] - originalGraph.size - 1] = false
                	}
                }
            }
        }

        print.append("$result\n")
    }
    print(print)
}