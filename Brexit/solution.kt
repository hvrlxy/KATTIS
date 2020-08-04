import java.util.Scanner
import java.io.*

fun main (){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCountries = sc.nextInt()
	val numEdges = sc.nextInt()
	val home = sc.nextInt()
	val source = sc.nextInt()

	val adjLists = Array<MutableList<Int>>(numCountries + 1){mutableListOf<Int>()}

	for (i in 0 until numEdges){
		val a = sc.nextInt()
		val b = sc.nextInt()
		adjLists[a].add(b)
		adjLists[b].add(a)
	}

	// for (i in 0 until numCountries + 1){
	// 	println(adjLists[i])
	// }

	val original = IntArray(numCountries + 1){adjLists[it].size}
	val checkBrexit = BooleanArray(numCountries + 1){true}
	val tracking = IntArray(numCountries + 1){adjLists[it].size}

	fun dfs(x: Int){
		checkBrexit[x] = false
		//println(checkBrexit.joinToString())
		for (i in adjLists[x]){
			//println("i = $i and checkBrexit[i] = ${checkBrexit[i]}")
			if (checkBrexit[i] == true){
				tracking[i] --
				if (tracking[i] <= original[i].toFloat()/2){
					dfs(i)
				}
			}
		}
	}

	dfs(source)
	if(checkBrexit[home] == true) println ("stay") else println("leave")
}