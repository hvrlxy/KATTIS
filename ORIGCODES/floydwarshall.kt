import kotlin.math.*

fun FloyWarshall(graph: Array<IntArray>): Array<IntArray>{
	val V = graph.size
	val result = Array<IntArray>(V){IntArray(V){Int.MAX_VALUE}}

	for (i in 0 until V){
		for (j in 0 until V){
			result[i][j] = graph[i][j]
		}
	}

	for (k in 0 until V){
		for (i in 0 until V){
			for (j in 0 until V){
				result[i][j] = min(result[i][k] + result[k][j], result[i][j])
			}
		}
	}
	return result
}