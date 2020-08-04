import java.util.Scanner
import java.io.*
import kotlin.math.abs

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val xPoint1 = sc.nextInt()
	val yPoint1 = sc.nextInt()
	val xPoint2 = sc.nextInt()
	val yPoint2 = sc.nextInt()
	val xPoint3 = sc.nextInt()
	val yPoint3 = sc.nextInt()

	val landArea = area(xPoint1, yPoint1, xPoint2, yPoint2, xPoint3, yPoint3)

	val numTree = sc.nextInt()
	var count = 0
	for (i in 0 until numTree){
		val x = sc.nextInt()
		val y = sc.nextInt()

		val sumArea = area(x, y, xPoint1, yPoint1, xPoint2, yPoint2) + 
		area(x, y, xPoint2, yPoint2, xPoint3, yPoint3) +
		area(x, y, xPoint1, yPoint1, xPoint3, yPoint3)

		if (sumArea == landArea){
			count ++
		}
	}

	println(landArea)
	println(count)
}

fun area(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int): Float{
	return (abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2))/2F)
}