import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numBoxes = sc.nextInt()
	val adj = Array<MutableList<Int>>(numBoxes){mutableListOf<Int>()}

	for(i in 0 until numBoxes){
		val idx = sc.nextInt()
		if (idx != 0) adj[idx - 1].add(i)
	}

	val numQueries = sc.nextInt()

	var aSet = mutableSetOf<Int>()

	fun dfs(v: Int){
		if (v !in aSet) aSet.add(v)
		for (w in adj[v]){
			if (w !in aSet) {
				aSet.add(w)
				dfs(w)
			}
		}
	}

	repeat(numQueries){
		val amount = sc.nextInt()
		aSet = mutableSetOf<Int>()
		repeat(amount){
			dfs(sc.nextInt() - 1)
		}
		println(aSet.size)
	}
}