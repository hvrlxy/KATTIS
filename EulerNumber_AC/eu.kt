import java.util.Scanner
import kotlin.math.*

fun main(){
	val sc = Scanner(System.`in`)

	val n = sc.nextInt()

	if (n > 16){
		println(E)
	}

	else{
		println(Euler(n))
	}
}

fun factorial(n: Int): Long{
	var result = 1L
	for (i in 1 .. n){
		result *= i
	}

	return result
}

fun Euler(n: Int): Double{
	var result = 1.0
	for (i in 1 .. n){
		result += 1.0/factorial(i)
	}

	return result
}