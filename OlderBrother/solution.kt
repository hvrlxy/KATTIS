import kotlin.math.*

fun main(){
	val n = readLine()!!.toInt()
	if (n <= 1) return println("no")

	val booleanArray = BooleanArray(50000){true}
	val primes = mutableListOf<Int>()

    for (i in 2 until 50000){
        if (booleanArray[i]){
        	primes.add(i)
            var j = 2
            while (i * j < 50000){
                booleanArray[i * j] = false
                j ++
            }
        }
    }

    for (i in primes){
    	if (n % i == 0){
    		val k = log(n.toDouble(), i.toDouble())

    		if (abs(k - k.toInt()) <= 1e-8) return println("yes") else return println("no")
    	}
    }

    return println("yes")
}