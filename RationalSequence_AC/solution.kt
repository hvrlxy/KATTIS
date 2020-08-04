import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCase = sc.nextInt()
	for (i in 0 until numCase){
		val caseNo = sc.nextInt()

		val pq = sc.next()

		val b = pq.split("/")

		val p = b[0].toLong()
		val q = b[1].toLong()

		if (p < q){
			println("$caseNo $q/${q - p}")
		}

		else if (p > q){
			val proot = p % q
			val qroot = q

			val p1root = qroot
			val q1root = qroot - proot

			println("$caseNo $p1root/${q1root + p1root * (p/q)}")
		}

		else{
			println("$caseNo 1/2")
		}
	}
}