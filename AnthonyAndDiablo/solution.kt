import java.util.Scanner
import java.io.*
import kotlin.math.*

fun main (args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val A = sc.nextDouble()
	val N = sc.nextDouble()

	val r = N / (2 * PI)
	val maxArea = r.pow(2.0) * PI

	if (maxArea < A){
		println("Need more materials!")
	}

	else{
		println("Diablo is happy!")
	}
}