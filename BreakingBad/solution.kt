import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val adj = Array<MutableList<Int>>(sc.nextInt()){mutableListOf<Int>()}

	val aMap = mutableMapOf<String, Int>()
	val array = Array<String>(adj.size){""}
	for (count in 0 until adj.size){
		val word = sc.next()
		aMap[word!!] = count
		array[count] = word
	}

	val numRemove = sc.nextInt()

	for (i in 0 until numRemove){
		val word1 = sc.next()
		val word2 = sc.next()
		adj[aMap[word1]!!].add(aMap[word2]!!)
		adj[aMap[word2]!!].add(aMap[word1]!!)
	}

	var d = IntArray(adj.size){0}

	fun dfs(v: Int): Boolean{
		if (d[v] == 0){
			d[v] = 1
		}

		for (w in adj[v]){
			if (d[w] == 0){
				d[w] = -1 * d[v]
				if (!dfs(w)) return false
			}
			else if (d[w] == d[v]){
				return false
			}
		}
		return true
	}

	for (i in 0 until d.size){
		if (d[i] == 0){
			if (!dfs(i)){
				println("impossible")
				return
			}
		}
	}
	val Aarray = mutableListOf<String>()
	val Barray = mutableListOf<String>()

	for (i in 0 until d.size){
		if (d[i] == 1){
			Aarray.add(array[i])
		}

		if (d[i] == -1){
			Barray.add(array[i])
		}
	}

	val aString = StringBuilder()
	val bString = StringBuilder()

	for (i in 0 until Aarray.size){
		aString.append(Aarray[i])
		aString.append(" ")
	}

	for (j in 0 until Barray.size){
		bString.append(Barray[j])
		bString.append(" ")
	}

	if (!aString.isEmpty()) aString.deleteCharAt(aString.length - 1)
	if (!bString.isEmpty()) bString.deleteCharAt(bString.length - 1)

	println(aString)
	println(bString)
}
