import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val aMap = mutableMapOf<String, Int>()

    val n = rd.readInt()
    val adj = Array<MutableList<Int>>(n){mutableListOf<Int>()}

    repeat(n){
    	val aString = rd.readStrings()
    	val filename = aString[0].substring(0, aString[0].length - 1)

    	if (filename !in aMap.keys){
    		aMap[filename] = aMap.size
    	}
    	for (i in 1 until aString.size){
    		if (aString[i] !in aMap.keys){
    			aMap[aString[i]] = aMap.size
    		}
    		adj[aMap[aString[i]]!!].add(aMap[filename]!!)
    	}
    }

    val keysArray = aMap.keys.toMutableList()
    val resultArray = mutableListOf<String>()

    val T = BooleanArray(n)
    fun dfs(v: Int){
    	if (!T[v]){
    		for (w in adj[v]){
    			if (!T[w]) dfs(w)
    		}
    		T[v] = true
    		resultArray.add(keysArray[v])
       	}
    }

    val recompileFile = rd.readLn()
    val recompileFileNum = aMap[recompileFile]!!

    dfs(recompileFileNum)
    resultArray.reverse()
    println(resultArray.joinToString(separator = "\n"))
}







