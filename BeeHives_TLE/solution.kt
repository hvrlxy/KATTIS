import java.io.*
import java.util.ArrayDeque

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var a = rd.readInts()

    val adj = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}

    repeat(a[1]){
        val a1 = rd.readInts()
        adj[a1[1]].add(a1[0])
        adj[a1[0]].add(a1[1])
    }

    fun bfs (v : Int, w: Int): Int{
        var d = IntArray(a[0]){30000}
        val Q = ArrayDeque<Int>(a[0])
        Q.addFirst(v)
        d[v] = 0

        while (Q.isNotEmpty()){
            val u = Q.removeFirst()
            if (u == v){
                for (x in adj[u]){
                    if (d[x] == 30000 && x != w){
                        d[x] = d[u] + 1
                        Q.add(x)
                    }
                }
            }
            else{
                for (x in adj[u]){
                    if (x == w) return d[u] + 1
                    if (d[x] == 30000){
                        d[x] = d[u] + 1
                        Q.add(x)
                    }
                }
            }
        }
        return d[w]
    }

    var smallestCycles = 30000
    for (v in 0 until a[0]){
        for (w in adj[v]){
            if (w > v){
                val c = bfs(v, w) + 1
                if (c < smallestCycles) smallestCycles = c
            }
        }
    }

    if (smallestCycles == 30000) println("impossible") else println(smallestCycles)
}







