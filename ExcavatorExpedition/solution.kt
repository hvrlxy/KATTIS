import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a = rd.readInts()
    val V = a[0]
    val E = a[1]

    val idxArray = IntArray(V){0}
    val aString = rd.readLn()

    for (i in 0 until V){
        if (aString[i] == '.') idxArray[i] = -1 else idxArray[i] = 1
    }
    idxArray[0] = 0
    idxArray[V - 1] = 0

    val adj = Array<MutableList<Int>>(V){mutableListOf<Int>()}

    for (i in 0 until E){
        val b = rd.readInts()
        adj[b[0]].add(b[1])
    }

    val d = IntArray(V){0}

    fun dfs(v: Int){
        if (d[v] == 0) d[v] = idxArray[v]
        for (w in adj[v]){
            if (d[w] == 0){
                d[w] = d[v] + idxArray[w]
                dfs(w)
            }
            else{
                d[w] = kotlin.math.max(d[w], d[v] + idxArray[w])
                dfs(w)
            }
        }
    }
    dfs(0)
    println(d[V - 1])
}