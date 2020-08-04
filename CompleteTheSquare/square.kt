import java.util.Scanner
import kotlin.math.*

fun main(){
	val sc = Scanner(System.`in`)

	val p1 = Point(sc.nextInt(), sc.nextInt())
	val p2 = Point(sc.nextInt(), sc.nextInt())
	val p3 = Point(sc.nextInt(), sc.nextInt())

	//println(distance(p1, p3))

	if (distance(p1, p2) > distance(p2, p3)){
		val xM = (p1.x + p2.x) / 2F
		val yM = (p1.y + p2.y) / 2F
		println("${(xM * 2 - p3.x).toInt()} ${(yM * 2 - p3.y).toInt()}")
	}

	else if (distance(p3, p2) > distance(p2, p1)){
		val xM = (p3.x + p2.x) / 2F
		val yM = (p3.y + p2.y) / 2F
		println("${(xM * 2 - p1.x).toInt()} ${(yM * 2 - p1.y).toInt()}")
	}

	else{
		val xM = (p3.x + p1.x) / 2F
		val yM = (p3.y + p1.y) / 2F
		println("${(xM * 2 - p2.x).toInt()} ${(yM * 2 - p2.y).toInt()}")	
	}
}

data class Point (val x: Int, val y: Int)

fun distance (p1: Point, p2: Point): Float{
	return (sqrt((p1.x.toFloat() - p2.x) * (p1.x - p2.x) + (p1.y.toFloat() - p2.y) * (p1.y - p2.y)))
}