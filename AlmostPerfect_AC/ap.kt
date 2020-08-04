import java.util.Scanner
import java.io.*
import kotlin.math.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	while (sc.hasNext()){
		val n = sc.nextLong()
		test(n)
	}
}

fun test(n: Long){
	val sumArray = mutableListOf<Long>()
	sumArray.add(1L)
	val sq = sqrt(n.toDouble()).roundToInt()
	//println("sqrt of n : $sq")
	for (i in 2..(sq)){
		if (n % i == 0L){
			sumArray.add(i.toLong())
			if ((i.toLong() != n/i) && (n/i !in sumArray)){
				//println("i : $i, n/i : ${n/i}")
				sumArray.add(n/i)
			}
		}
	}

	//println("array: ${sumArray.joinToString()}")

	if (sumArray.sum() == n){
		println("$n perfect")
	}

	else if ((sumArray.sum() == n + 1) || (sumArray.sum() == n - 1) || (sumArray.sum() == n + 2) || (sumArray.sum() == n - 2)){
		println("$n almost perfect")
	}
	else{
		println("$n not perfect")
	}
}