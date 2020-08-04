import java.util.Scanner
import java.io.*
import kotlin.math.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCases = sc.nextInt()

	for (i in 0 until numCases){
		val direction = sc.next()

		val change = sc.nextInt()
		val currentHour = sc.nextInt()
		val currentMin = sc.nextInt()

		if (direction == "F"){
			val totalMin = currentHour * 60 + currentMin
			var newMin = (totalMin + change) % 1440

			if (newMin < 0){
				newMin += 1440
			}

			println("${newMin/60} ${newMin%60}")
		}

		else{
			val totalMin = currentHour * 60 + currentMin
			var newMin = (totalMin - change) % 1440

			if (newMin < 0){
				newMin += 1440
			}

			println("${newMin/60} ${newMin%60}")
		}
	}
}