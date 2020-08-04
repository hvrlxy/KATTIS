import java.util.Scanner
import java.io.*
import kotlin.math.*

fun main (args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val x = sc.nextLong()
	if (x >= 9){
		println(1 - 1/E)
	}
	else{
		println(derangement(x))
	}
}

fun derangement (x: Long): Float{
	var result = 1F
	for (i in 1 .. x){
		if (i % 2 == 0L){
			result += 1F/factorial(i)
		}
		else{
			result -= 1F/factorial(i)
		}
	}
	return (1 - result)
}

fun factorial(x: Long): Long{
	var result = 1L
	for (i in 1 .. x){
		result *= i
	}
	return result
}