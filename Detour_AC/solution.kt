import java.io.*
import java.util.PriorityQueue
import java.util.ArrayDeque

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toIntArray() // list of ints

data class Edge (val v: Int, val weight: Long){ // this one store the edge{
    override fun toString() = "[$v, $weight]"
}

data class Vertex (val v: Int, val weight: Long): Comparable<Vertex>{
	// this data structure tells us the min distance of a vertex at current time
	override fun compareTo(other: Vertex) = this.weight.compareTo(other.weight)
    override fun toString() = "[$v, $weight]"
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var a = rd.readInts()
    val adj = Array<MutableList<Edge>>(a[0]){mutableListOf<Edge>()}

    repeat(a[1]){
        val aline = rd.readStrings()
        adj[aline[0].toInt()].add(Edge(aline[1].toInt(), aline[2].toLong()))
        adj[aline[1].toInt()].add(Edge(aline[0].toInt(), aline[2].toLong()))
    }

    val d = LongArray(a[0]){Long.MAX_VALUE}
    val p = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}
    d[0] = 0

    val Q = PriorityQueue<Vertex>()
    Q.add(Vertex(0,0))
    while (Q.isNotEmpty()){
        val u = Q.poll()

        for (i in adj[u.v]){
            if (u.weight + i.weight < d[i.v]){
                d[i.v] = u.weight + i.weight
                p[i.v] = mutableListOf(u.v)
                Q.add(Vertex(i.v, d[i.v]))
            }
            else if (u.weight + i.weight == d[i.v]){
                p[i.v].add(u.v)
            }
        }
    }

    fun removeEdge(v: Int){
        if (p[v].isEmpty()) return
        for (w in adj[p[v]]){
            if (w.v == v) {
                adj[p[v]].remove(w)
                removeEdge(p[v])
                break
            }
        }
    }

    removeEdge(1)

    val newP = IntArray(a[0]){-1}
    newP[0] = 0

    val Queue = ArrayDeque<Int>()
    Queue.add(0)

    while (Queue.isNotEmpty()){
        val v = Queue.getFirst()
        Queue.removeFirst()
        for (w in adj[v]){
            if (newP[w.v] == -1){
                newP[w.v] = v
                Queue.add(w.v)
            }
        }
    }

    if (newP[1] == -1) return println("impossible")

    val resultList = ArrayDeque<Int>()

    fun findPath(v: Int){
        resultList.addFirst(v)
        if (newP[v] == v) return
        findPath(newP[v])
    }

    findPath(1)

    println("${resultList.size} ${resultList.joinToString(separator = " ")}")
}