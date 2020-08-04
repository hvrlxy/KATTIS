import java.util.Scanner
import java.io.*
import kotlin.math.abs

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	var i = 1
	while (sc.hasNext()){
		println("Case $i:")
		val n = sc.nextInt()

		val numArray = IntArray(n){0}

		for (j in 0 until n){
			numArray[j] = sc.nextInt()
		}

		val m = sc.nextInt()

		for (j in 0 until m){
			val query = sc.nextInt()

			var result = Int.MAX_VALUE

			var result2 = 0
			for (a in 0 until numArray.size){
				for (b in a + 1 until numArray.size){
					if (abs(numArray[a] + numArray[b] - query) < result){
						result2 = numArray[a] + numArray[b]
						result = abs(numArray[a] + numArray[b] - query)
					}
				}
			}

			println("Closest sum to $query is $result2.")
		}
		i++
	}
}