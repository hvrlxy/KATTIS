import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val moneyArray = Array<Int>(sc.nextInt()){0}
	val adj = Array<MutableList<Int>>(moneyArray.size){mutableListOf<Int>()}
	val numEdges = sc.nextInt()

	for (i in 0 until moneyArray.size){
		moneyArray[i] = sc.nextInt()
	}

	for (i in 0 until numEdges){
		val a = sc.nextInt()
		val b = sc.nextInt()
		adj[a].add(b)
		adj[b].add(a)
	}

	val checkArray = BooleanArray(moneyArray.size){false}
	var count = 0

	fun dfs(v: Int){
		checkArray[v] = true
		count += moneyArray[v]
		for (w in adj[v]){
			if (checkArray[w] == false){
				dfs(w)
			}
		}
	}

	for (v in 0 until checkArray.size){
		if (checkArray[v] == false){
			//countPeople = 0
			dfs(v)
			//println(count)
			if (count != 0) return println("IMPOSSIBLE")
		}
	}
	println("POSSIBLE")
}