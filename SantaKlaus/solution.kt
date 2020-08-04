import java.util.Scanner
import java.io.*
import kotlin.math.*

fun convert(x: Double): Double{
	return x*PI/180.0
}

fun main (args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))
	val height = sc.nextInt()
	val angle = sc.nextDouble()

	if ((0.0 <= angle) && (angle <= 180.0)){
		println("safe")
	}

	else if (angle == 270.0){
		println(height)
	}

	else if (angle > 180.0 && angle < 270.0){
		val a = (height/ cos(convert(270.0 - angle)))
		println(a.toInt())
	}

	else{
		val a = (height/ sin(convert(360.0 - angle)))
		println(a.toInt())
	}
}

