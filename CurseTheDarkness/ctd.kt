import java.util.Scanner
import java.io.*
import kotlin.math.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCase = sc.nextInt()

	for (i in 0 until numCase){
		val yourX = sc.nextDouble()
		val yourY = sc.nextDouble()
		val numCandles = sc.nextInt()

		var result = false

		for (j in 0 until numCandles){
			val x = sc.nextDouble()
			val y = sc.nextDouble()

			if (distance(yourX, yourY, x, y) <= 8){
				result = true
			}
		}

		if(result == false){
			println("curse the darkness")
		}

		else{
			println("light a candle")
		}
	}
}

fun distance(x1: Double, y1: Double, x2: Double, y2: Double): Double{
	return (sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)))
}