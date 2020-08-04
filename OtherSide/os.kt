import java.util.Scanner
import kotlin.math.*

fun main(){
	val sc = Scanner(System.`in`)

	val W = sc.nextInt()
	val S = sc.nextInt()
	val C = sc.nextInt()
	val K = sc.nextInt()

	val min = min(W + C, S)
	val max = max(W + C, S)

	if (K > min){
		println("YES")
		return
	}

	else if ((K == min) && (2 * min >= max)){
		println("YES")
		return
	}

	else {
		println("NO")
	}
}