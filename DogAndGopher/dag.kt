import java.util.Scanner
import java.io.*
import kotlin.math.sqrt

var xG: Double = 0.0
var yG: Double = 0.0
var xD: Double = 0.0
var yD: Double = 0.0

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	xG = sc.nextDouble()
	yG = sc.nextDouble()

	xD = sc.nextDouble()
	yD = sc.nextDouble()

	while (sc.hasNext()){
		val x = sc.next()
		val y = sc.next()

		if (distanceGopher(x.toDouble(),y.toDouble()) < distanceDog(x.toDouble(),y.toDouble())){
			println("The gopher can escape through the hole at ($x,$y).")
			return
		}
	}

	println("The gopher cannot escape.")
}

fun distanceGopher (x: Double, y: Double): Double{
	return sqrt((xG - x) * (xG - x) + (yG - y) * (yG - y))
}

fun distanceDog (x: Double, y: Double): Double{
	return sqrt((xD - x) * (xD - x) + (yD - y) * (yD - y)) / 2
}