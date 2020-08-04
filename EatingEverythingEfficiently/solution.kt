import java.util.Scanner
import java.io.*
import kotlin.math.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val n = sc.nextInt()
	val m = sc.nextInt()

	val wArray = DoubleArray(n){sc.nextDouble()}

	val path = Array<MutableList<Int>>(n){mutableListOf<Int>()}
	for (i in 0 until m){
		path[sc.nextInt()].add(sc.nextInt())
	}

	var d = DoubleArray(n){-1.0}

	fun dfs(v: Int) {
		d[v] = wArray[v]
		for (i in path[v]){
			//val a: Double
			if (d[i] == -1.0){
				dfs(i)
			}
			d[v] = maxOf(d[v], d[i], wArray[v] + 0.5 * d[i])
		}
	}

	dfs(0)

	println(d[0])
}