import java.io.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()
    val forcedGraph = Array<MutableList<Int>>(s[0] + 1){mutableListOf<Int>()}
    val directedGraph = Array<MutableList<Int>>(s[0] + 1){mutableListOf<Int>()}

    repeat(s[1]){
    	val e = rd.readInts()

    	if (e[0] < 0){
    		forcedGraph[- e[0]].add(e[1])
    	}
    	else{
    		directedGraph[e[0]].add(e[1])
    	}
    }

    var count = 0
    val d = BooleanArray(s[0] + 1)

    fun dfs(v: Int, bug: Int){
    	if (!d[v]) d[v] = true
    	if (directedGraph[v].isEmpty() && forcedGraph[v].isEmpty()) count ++
    	else if (forcedGraph[v].isEmpty()){
    		count ++
    	}

    	for (w in forcedGraph[v]){
    		if (!d[w]) dfs(w,bug)
    	}

    	if (bug == 0){
    		for (u in directedGraph[v]){
    			if (!d[u]) dfs(u, 1)
    		}
    	}
    }

    dfs(1, 0)

    println(count)
}