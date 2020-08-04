import java.io.*
import kotlin.math.min

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var a = rd.readInts()
    while (a[0] + a[1] != 0){
        val adj = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}

        repeat(a[1]){
            val a1 = rd.readInts()
            adj[a1[1]].add(a1[0])
            adj[a1[0]].add(a1[1])
        }

        val indices = IntArray(adj.size){-1}
        val stack = mutableListOf<Int>()
        var c = adj.size

        // create another parameter u which is the parent of the vertex v
        fun dfs(v: Int, u: Int){
            stack.add(v)
            indices[v] = stack.size - 1
            for (w in adj[v]){
                if (indices[w] == -1){
                    dfs(w, v)
                }

                if (w != u){
                    // check whether w is not the parent of v, if it is, then don't update the indices
                    indices[v] = min(indices[w], indices[v])
                }
            }

            if (stack[indices[v]] == v){
                c ++
                while (indices[v] < stack.size){
                    indices[stack[stack.size - 1]] = c
                    stack.removeAt(stack.size - 1)
                }
            }
        }

        dfs(0, -1)

        var yOrN = false
        for (i in 0 until indices.size - 1){
            if (indices[i] != indices[i + 1]) yOrN = true
        }

        if (yOrN) println("Yes") else println("No")
        a = rd.readInts()
    }
}
    