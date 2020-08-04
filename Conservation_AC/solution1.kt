import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()
    repeat(numCase){
    	val a = rd.readInts()

    	val labNum = rd.readInts()

    	var adj = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}

    	var indegreeArray = IntArray(a[0]){0}
    	repeat(a[1]){
    		val dependency = rd.readInts()
    		adj[dependency[0] - 1].add(dependency[1] - 1)
    		indegreeArray[dependency[1] - 1] ++
    	}

    	val Q = mutableListOf<Int>()
    	for (v in 0 until indegreeArray.size){
    		if (indegreeArray[v] == 0) Q.add(v)
    	}

    	fun findCurrentLab(): Int{
    		var lab1 = 0
    		var lab2 = 0
    		for (v in Q){
    			if (labNum[v] == 1) lab1 ++ else lab2 ++
    		}
    		if (lab1 >= lab2) return 1 else return 2
    	}

    	var currentLab = findCurrentLab()

    	var result = 0
    	while (Q.isNotEmpty()){
    		var v = -1
    		for (i in Q){
    			if (labNum[i] == currentLab){
    				v = i
    				Q.remove(i)
    				break
    			}
    		}
            println(v)
    		if (v == -1) {
    			currentLab = 3 - currentLab
    			result ++
    		}
    		else {
    			for (w in adj[v]){
    				indegreeArray[w] --
    				if (indegreeArray[w] == 0) Q.add(w)
    			}
    		}
    	}

    	println(result)
    }
}