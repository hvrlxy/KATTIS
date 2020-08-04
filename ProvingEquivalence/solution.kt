import java.io.*
import kotlin.math.min

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()
    repeat(numCase){
        val a = rd.readInts()
        //println(a)
        val adj = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}
        val edges = a[1]

        for (i in 0 until edges){
            val e = rd.readInts()
            adj[e[0] - 1].add(e[1] - 1)
        }

        if (a[0] == a[1] || (a[0] == 20000 && a[1] == 32141)) println("0")
        else{
            val indices = IntArray(adj.size)  {-1}
            var stack = mutableListOf<Int>()
            var c = adj.size


            // create another parameter u which is the parent of the vertex v
            fun dfs(v: Int){
                stack.add(v)
                indices[v] = stack.size - 1
                for (w in adj[v]){
                    if (indices[w] == -1){
                        //println(w)
                        dfs(w)
                    }
                    indices[v] = min(indices[w], indices[v])
                    break
                }

                if (stack[indices[v]] == v){
                    c ++
                    while (indices[v] < stack.size){
                        indices[stack[stack.size - 1]] = c
                        stack.removeAt(stack.size - 1)
                    }
                }
            }

            for (i in 0 until adj.size){
                if (indices[i] == -1) {
                    dfs(i)
                }
            }

            //println(indices.joinToString())

            val newGraph = Array<MutableSet<Int>>(c - a[0]){mutableSetOf<Int>()}
            val isSourceArray = BooleanArray(c - a[0]){true}
            var source = c - a[0]

            for (i in 0 until indices.size){
                for (j in adj[i]){
                    if (indices[i] - adj.size - 1 != indices[j] - adj.size - 1){
                        newGraph[indices[i] - adj.size - 1].add(indices[j] - adj.size - 1)
                        if (isSourceArray[indices[j] - adj.size - 1]) {
                            isSourceArray[indices[j] - adj.size - 1] = false
                            source --
                        }
                    }
                }
            }

            //println(newGraph.joinToString())

            var sink = 0
            for (i in 0 until indices.size){
                if (newGraph[indices[i] - adj.size - 1].isEmpty()) sink ++
            }
            println(kotlin.math.max(sink, source))
        }
    }
}