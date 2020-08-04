import java.io.*
import kotlin.math.abs

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))
    val numCases = rd.readInt()
    repeat(numCases){
        val adj = Array<MutableList<Int>>(rd.readInt() + 2){mutableListOf<Int>()}
        val locations = mutableListOf<Pair<Int, Int>>()

        repeat(adj.size){
            val a = rd.readInts()
            locations.add(a[0] to a[1])
        }

        fun dist(x: Pair<Int, Int>, y: Pair<Int, Int>): Boolean{
            if (abs(x.first - y.first) + abs(x.second - y.second) <= 1000) return true
            return false
        }

        for (i in 0 until locations.size){
            for (j in i+1 until locations.size){
                if (dist(locations[i], locations[j])){
                    adj[i].add(j)
                    adj[j].add(i)
                }
            }
        }

        val d = BooleanArray(adj.size){false}

        fun dfs(v: Int){
            d[v] = true

            for (w in adj[v]){
                if (!d[w]) dfs(w)
            }
        }

        dfs(0)
        if (d[d.size - 1]) println("happy") else println("sad")
    }
}