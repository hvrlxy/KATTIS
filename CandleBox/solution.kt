import java.util.Scanner
import java.io.*

fun main (){
	val sc = Scanner(System.`in`)

	val D = sc.nextInt()
	val R = sc.nextInt()
	val T = sc.nextInt()

	var upto = 3

	while ((upto + D + 4)*(upto + D - 3)/2 <= R){
		val x = (upto + 3)*(upto - 2)/2 - T
		if ((T == (upto + 3)*(upto - 2)/2 - x) && 
			(R == (upto + D + 4)*(upto + D - 3)/2 + x)) {
			println(x)
			return
		}
		else{
			upto ++
		}
	}
}