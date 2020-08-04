import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    var result = 0

    val aline = rd.readStrings()
    val wordMap = mutableMapOf<String, Int>()
    for (w in aline){
    	if (w !in wordMap.keys) wordMap[w] = 1
    	else{
    		val c = wordMap[w]!!
    		wordMap[w] = c + 1
    	}
    }

    val adj = mutableMapOf<String, MutableList<String>>()
    repeat(s[1]){
    	val s1 = rd.readStrings()
    	if (s1[0] !in adj.keys) adj[s1[0]] = mutableListOf(s1[1]) else adj[s1[0]]!!.add(s1[1])
    	if (s1[1] !in adj.keys) adj[s1[1]] = mutableListOf(s1[0]) else adj[s1[1]]!!.add(s1[0])
    }

    val wordList = wordMap.keys.toList()

    val lengthArray = IntArray(wordList.size){wordList[it].length}

    var dArray = mutableSetOf<String>()

    fun dfs(v: String): Int{
    	dArray.add(v)
    	var result = v.length
    	if (v !in adj.keys) return result
    	for (w in adj[v]!!){
    		if (w !in dArray){
    			val l = dfs(w)
    			if (l < result) result = l
    		}
    	}
    	return result
    }

    for (i in 0 until lengthArray.size){
    	dArray = mutableSetOf<String>()
    	lengthArray[i] = dfs(wordList[i])
    	result += wordMap[wordList[i]]!! * lengthArray[i]
    }
    
    println(result)
}