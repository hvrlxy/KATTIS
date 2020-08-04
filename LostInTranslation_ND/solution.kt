import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val aMap = mutableMapOf<String, Int>()

    val targets = rd.readStrings()

    aMap["English"] = 0

    val adjList = mutableListOf(mutableListOf<Pair<Int, Int>>())
    var count = 1

    repeat(s[1]){
    	val aline = rd.readStrings()

    	if (aline[0] !in aMap.keys){
    		aMap[aline[0]] = count
    		count ++
    		adjList.add(mutableListOf<Pair<Int, Int>>())
    	}
    	if (aline[1] !in aMap.keys){
    		aMap[aline[1]] = count
    		count ++
    		adjList.add(mutableListOf<Pair<Int, Int>>())
    	}

    	adjList[aMap[aline[0]]!!].add(Pair(aMap[aline[1]]!!, aline[2].toInt()))
    	adjList[aMap[aline[1]]!!].add(Pair(aMap[aline[0]]!!, aline[2].toInt()))

    }

    //println(adjList)

    val C = IntArray(aMap.keys.toList().size){Int.MAX_VALUE}
    val D = IntArray(aMap.keys.toList().size){Int.MAX_VALUE}
    val P = IntArray(aMap.keys.toList().size){Int.MAX_VALUE}

    C[0] = 0
    D[0] = 0
    P[0] = 0

    val Q = mutableListOf<Int>()

    Q.add(0)

    var result = 0

    while (Q.isNotEmpty()){
    	val v = Q[0]
    	Q.removeAt(0)

    	for (w in adjList[v]){
    		if (D[w.first] > D[v] + 1){
    			if (P[w.first] != Int.MAX_VALUE) result = result - C[P[w.first]]
    			P[w.first] = v
    			D[w.first] = D[v] + 1
    			C[w.first] = C[v] + w.second
    			result += w.second
    			Q.add(w.first)
    		}
    		else if (D[w.first] == D[v] + 1 && C[w.first] > C[v] + w.second){
    			result = result - C[P[w.first]]
    			P[w.first] = v
    			C[w.first] = C[v] + w.second
    			result += w.second
    			Q.add(w.first)
    		}
    	}
    }

    println(result)
}










