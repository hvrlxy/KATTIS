import java.io.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val size = rd.readInt()
    val adj = Array<MutableList<Int>>(size){mutableListOf<Int>()}

    for (i in 0 until size){
    	val s = rd.readLn()
    	for (j in 0 until size){
    		if (s[j] == '1') adj[i].add(j)
    	}
    }

    val d = BooleanArray(size){false}
    val resultList = mutableListOf<Int>()


    fun dfs(v: Int){
    	resultList.add(0, v)
    	if (!d[v]) d[v] = true

    	for (w in adj[v]){
    		if (!d[w]) {
    			dfs(w)
    		}
    	}
    }

    dfs(0)

    for (i in 0 until size){
    	if (!d[i]) return println("impossible")
    }
    println(resultList.joinToString(separator = " "))
}