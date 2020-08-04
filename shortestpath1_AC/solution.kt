import java.io.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

data class Edge (var head: Int, var weight: Int)

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var a = rd.readInts()
    var result = StringBuilder()

    while (a.sum() != 0){
        var adj = Array<MutableList<Edge>>(a[0]){mutableListOf<Edge>()}    //create the adjacency list

        for (i in 0 until a[1]){
        	val a1 = rd.readInts()
            adj[a1[0]].add(Edge(a1[1], a1[2]))
        }

        val d = IntArray(a[0]){Int.MAX_VALUE}
        d[a[3]] = 0

        var S = mutableListOf<Int>()
        var Sbar = mutableListOf<Int>()
        Sbar.add(a[3])

        fun findSmallest(): Int?{
            var min = Int.MAX_VALUE
            var v : Int? = null

            for (i in Sbar){
                if (d[i] < min){
                    v = i
                    min = d[i]
                }
            }
            return v
        }
        var v = findSmallest()
        while (v != null){
            S.add(v!!)
            Sbar.remove(v!!)

            for (w in adj[v!!]){
                if (w.head !in S && d[w.head] > d[v!!] + w.weight){
                    d[w.head] = d[v!!] + w.weight
                    if (w.head !in Sbar) Sbar.add(w.head)
                }
            }
            v = findSmallest()
        }

        for (i in 0 until a[2]){
            val position = rd.readInt()
            if (d[position] == Int.MAX_VALUE) result.append("Impossible\n") else result.append("${d[position]}\n")
        }

        a = rd.readInts()

        if (a.sum() != 0) result.append("\n")
    }
    print(result)
}