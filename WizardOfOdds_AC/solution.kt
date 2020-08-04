import java.util.Scanner
import java.io.*
import java.math.BigInteger

fun main(){
	val sc = Scanner(System.`in`)

	val N = sc.next()
	val K = sc.nextInt()

	val N1 = BigInteger(N)

	if (1L.toBigInteger().shl(K).compareTo(N1) >= 0){
		println("Your wish is granted!")
	}
	else{
		println("You will become a flying monkey!")
	}
}