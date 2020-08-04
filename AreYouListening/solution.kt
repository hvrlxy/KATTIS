import java.util.Scanner
import java.io.*
import kotlin.math.*

var x: Int = 0
var y: Int = 0

fun main (args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	x = sc.nextInt()
	y = sc.nextInt()

	val numListeners = sc.nextInt()

	val array = IntArray(numListeners){0}

	for (i in 0 until numListeners){
		val x1 = sc.nextInt()
		val y1 = sc.nextInt()

		val radius = sc.nextInt()
		array[i] = maxDistance(x1, y1, radius)
	}

	array.sort()
	//println(array.joinToString())

	println(array[2])
}

fun maxDistance (x1: Int, y1: Int, radius: Int): Int{
	val pointDistance = sqrt(((x - x1).toDouble()).pow(2.0) + ((y - y1).toDouble()).pow(2.0))
	if (pointDistance <= radius){
		return (0)
	}
	var maxD = (pointDistance - radius).toInt()
	return (maxD)
}