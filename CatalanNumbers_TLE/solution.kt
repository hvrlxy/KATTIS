import java.util.Scanner
import java.io.*
import java.math.BigInteger
import java.math.BigDecimal

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val catalan = Array<BigInteger>(5001){"1".toBigInteger()}

	for (i in 2 until catalan.size){
		val coefficient = (2.0 * (2 * i + 1) / (i + 2.0)).toString().toBigDecimal() 
		catalan[i] = catalan[i - 1].times(coefficient)
	}

	val numCases = sc.nextInt()
	for (i in 0 until numCases){
		println(catalan[sc.nextInt()])
	}
}