import java.util.Scanner
import kotlin.math.abs

fun main(){
	val sc = Scanner(System.`in`)

	val p1 = (sc.nextInt() to sc.nextInt())
	val p2 = (sc.nextInt() to sc.nextInt())

	val battery = sc.nextInt()

	val manhattanDistance = abs(p1.first - p2.first) + abs(p1.second - p2.second)

	if (battery - manhattanDistance >= 0 && (battery - manhattanDistance) % 2 == 0) println("Y") else println("N")
}