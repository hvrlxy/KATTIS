import java.util.Scanner
import java.io.*
import java.math.BigInteger
// import kotlin.math.pow

fun main(){
	val sc = Scanner(System.`in`)
	var base = sc.nextInt()

	while (base != 0){

		//val base = sc.nextInt()
		val aString = sc.next()
		val bString = sc.next()

		val p = BigInteger(aString, base)
		val m = BigInteger(bString, base)

		val result = p.mod(m).toString(base)

		println(result)

		base = sc.nextInt()
	}
}