import java.util.Scanner
import java.io.*
import kotlin.math.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCase = sc.nextInt()

	for (i in 0 until numCase){
		val numDarts = sc.nextInt()

		var result = 0

		for (j in 0 until numDarts){
			val x = sc.nextInt()
			val y = sc.nextInt()

			if (distance(x, y) > 200){
				result = result
			}
			else{
				val d = distance(x,y)
				if (d <= 20){
					result += 10
				}
				else if (d <= 40){
					result += 9
				}
				else if (d <= 60){
					result += 8
				}
				else if (d <= 80){
					result += 7
				}
				else if (d <= 100){
					result += 6
				}
				else if (d <= 120){
					result += 5
				}
				else if (d <= 140){
					result += 4
				}
				else if (d <= 160){
					result += 3
				}
				else if (d <= 180){
					result += 2
				}
				else if (d <= 200){
					result +=1
				}
			}
		}
		println(result)
	}
}

fun distance(x: Int, y: Int): Float{
	return sqrt(x.toFloat() * x + y.toFloat() * y)
}