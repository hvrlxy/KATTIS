import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()

fun pow (b: Int, e: Int): Int{
	var result = 1
	repeat(e){
		result *= b
	}
	return result
}

fun findFactor(p: Int, n: Int): Int{
	if (p > n) return 0
	var i = 1
	var result = 0
	while (n/pow(p,i) != 0){
		result += n/pow(p,i)
		i ++
	}
	return result
}

fun sieve(n: Int): MutableList<Int>{
	var array = mutableListOf<Int>()

	val bArray = BooleanArray(n + 1){true}
	for (i in 2 .. n){
		if (bArray[i]){
			array.add(i)
			var j = 2
			while (i * j <= n){
				bArray[i * j] = false
				j ++
			}
		}
	}

	return array
}

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var aString = rd.readLine()
	while (aString != null){
		val a = aString.split(" ").map{ it.toInt() }

		val primeArray = sieve(a[0])
		//println(primeArray)
		val aMap = mutableMapOf<Int, Int>()

		var i = 0
		for (p in primeArray){
			aMap[p] = i
			i ++
		}

		val factorization = IntArray(primeArray.size){0}

		val n = a[0]
		val k = a[1]

		for (p in primeArray){
			factorization[aMap[p]!!] = findFactor(p, n) - findFactor(p, n - k) - findFactor(p, k)
		}

		//println(factorization.joinToString())

		var result = 1L
		for (i in factorization){
			result *= (i + 1)
		}
		println(result)
		aString = rd.readLn()
	}
}








