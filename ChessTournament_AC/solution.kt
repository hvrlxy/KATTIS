import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a = rd.readInts()

    val firstGraph = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}
    val equalGraph = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}

    val contractVertices = IntArray(a[0]){-1}
    var vertex = 0

    repeat(a[1]){
    	val aline = rd.readStrings()

    	if (aline[1] == ">") firstGraph[aline[0].toInt()].add(aline[2].toInt())
    	else{
    		equalGraph[aline[0].toInt()].add(aline[2].toInt())
    		equalGraph[aline[2].toInt()].add(aline[0].toInt())
    	}
    }

    fun dfs(v: Int){
    	contractVertices[v] = vertex
    	for (w in equalGraph[v]){
    		if (contractVertices[w] == -1){
    			//contractVertices[w] = vertex
    			dfs(w)
    		}
    	}
    }

    for (i in 0 until a[0]){
    	if (contractVertices[i] == -1) {
    		dfs(i)
    		vertex ++
    	}
    }

    val newGraph = Array<MutableSet<Int>>(vertex){mutableSetOf<Int>()}
    val indegreeArray = IntArray(vertex){0}

    for (i in 0 until contractVertices.size){
    	for (j in firstGraph[i]){
    		if (contractVertices[i] == contractVertices[j]) return println("inconsistent")
    		else {
    			if (contractVertices[j] !in newGraph[contractVertices[i]]) indegreeArray[contractVertices[j]] ++
    			newGraph[contractVertices[i]].add(contractVertices[j])
    		}
    	}
    }

    val T = IntArray(vertex){Int.MAX_VALUE}

    fun topSort(): Boolean{
    	var count = 0
    	val Q = java.util.ArrayDeque<Int>()
    	for (i in 0 until vertex){
    		if (indegreeArray[i] == 0) Q.add(i)
    	}

    	if (Q.isEmpty()) return false

    	while (Q.isNotEmpty()){
    		val v = Q.getFirst()
    		//println(v)
    		Q.removeFirst()
    		val alist = newGraph[v].toList()
    		T[v] = count
    		count ++
    		for (w in alist){
    			indegreeArray[w] --
    			//if (v == 48999) println(w)
    			if (T[w] != Int.MAX_VALUE) {
    				return false
    			}
    			else if (indegreeArray[w] == 0) Q.add(w)
    		}
    	}

    	for (i in 0 until T.size){
    		if (T[i] == Int.MAX_VALUE) return false
    	}
    	return true
    }

    if (topSort()) println("consistent") else println("inconsistent")
}








