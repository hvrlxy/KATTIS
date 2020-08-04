import java.math.BigInteger
import kotlin.math.*

fun main(){
	var n = readLine()!!.toLong()
	val booleanArray = BooleanArray(1000000){true}
	val primes = mutableListOf<Long>()

    for (i in 2 until 1000000){
        if (booleanArray[i]){
        	primes.add(i.toLong())
            var j = 2
            while (i * j < 1000000){
                booleanArray[i * j] = false
                j ++
            }
        }
    }

	val aMap = mutableMapOf<Long, Int>()
	
	loop@for (i in primes){
		while (n % i == 0L){
			if (i !in aMap.keys) aMap[i] = 1
			else {
				val c = aMap[i]!!
				aMap[i] = c + 1
			}
			n /= i
			if (n == 1L) break@loop
		}
	}

	if (n > 1L) aMap[n] = 1

	var maxBase = 0L
	var max = 0
	for (b in aMap.keys){
		if (aMap[b]!! > max){
			max = aMap[b]!!
			maxBase = b
		}
	}
	println(maxBase)
}