import java.util.Scanner
import java.io.*

data class Edge(var head: Int, var weight: Int)

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val N = sc.nextInt()
	val source = sc.nextInt()
	val des = sc.nextInt()

	val adj = Array<MutableList<Edge>>(N){mutableListOf<Edge>()}

	for (i in 0 until N){
		adj[sc.nextInt()].add(Edge(sc.nextInt(), sc.nextInt()))
	}

	val d = IntArray(N){Int.MAX_VALUE}
	d[source] = 0

	var S = mutableListOf<Int>()

	fun findSmallest(): Int?{
		var min = Int.MAX_VALUE
		var v : Int? = null

		for (i in 0 until d.size){
			if (i !in S && d[i] < min){
				v = i
				min = d[i]
			}
		}
		return v
	}

	while (findSmallest() != null){
		val v : Int = findSmallest()!!

		S.add(v)

		for (w in adj[v]){
			if (w.head !in S && d[w.head] > d[v] + w.weight){
				d[w.head] =  w.weight
			}
		}
	}

	println(d[des])
}