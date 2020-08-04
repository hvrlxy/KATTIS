import java.util.Scanner
import java.io.*
import kotlin.math.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))
	val numCase = sc.nextInt()
	var result = StringBuilder()
	var caseNo = 0
	repeat(numCase){
		caseNo ++
		result.append("Case #$caseNo: ")
		val vertex1 = (sc.nextDouble() to sc.nextDouble())
		val vertex2 = (sc.nextDouble() to sc.nextDouble())
		val vertex3 = (sc.nextDouble() to sc.nextDouble())

		if (!isTriangle(vertex1, vertex2, vertex3)) result.append("not a triangle\n")
		else{
			if (isIsosceles(x1,y1,z1)) result.append("isosceles ") else result.append("scalene ")
			if (isRight(vertex1, vertex2, vertex3)) result.append("right triangle\n")
			else if (isAcute(vertex1, vertex2, vertex3)) result.append("acute triangle\n")
			else result.append("obtuse triangle\n")
		}
	}
	print(result)
}

fun isTriangle(x: Pair<Double, Double>, y: Pair<Double, Double>, z: Pair<Double, Double>): Boolean {
	val area = abs(x.first * (y.second - z.second) + y.first * (z.second - x.second) + z.first * (x.second - y.second))/2.0
	if (area > 0) return true
	return false
}

fun squareDist (x: Pair<Double, Double>, y: Pair<Double, Double>): Double = (y.first - x.first).pow(2.0) + (y.second - x.second).pow(2.0)

fun isRight(x: Pair<Double, Double>, y: Pair<Double, Double>, z: Pair<Double, Double>): Boolean{
	val a = squareDist(x, y)
	val b = squareDist(y, z)
	val c = squareDist(x, z)

	return (a + b == c || a + c == b || b + c == a)
}

fun isIsosceles(x: Double, y: Double, z: Double): Boolean = (x == y || y == z || z == x)

fun isAcute(x: Pair<Double, Double>, y: Pair<Double, Double>, z: Pair<Double, Double>): Boolean{
	val a = squareDist(x, y)
	val b = squareDist(y, z)
	val c = squareDist(x, z)

	var a1 = DoubleArray(3){0.0}
	a1[0] = a
	a1[1] = b
	a1[2] = c
	a1.sort()
	if (a1[0] + a1[1] > a1[2]) return true
	return false
}