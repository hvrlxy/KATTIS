import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	val x = sc.nextDouble()
	val y = sc.nextDouble()

	if (y == 1.0 && x == 0.0)  return println("ALL GOOD")
	else if (y == 1.0) return println("IMPOSSIBLE")
	println(-x / (y - 1))
}