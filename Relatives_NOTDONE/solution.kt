import kotlin.math.*

fun main(){
	var n = readLine()!!.toLong()

	val primeSet = mutableSetOf<Int>()
	val bArray = java.util.BitSet(500001)
    bArray.flip(2, bArray.size() - 1)

    for (i in 2 .. 500000){
        if (bArray[i]){
        	primeSet.add(i)
            var j = 2
            while (i * j <= 500000){
                bArray.clear(i * j)
                j ++
            }
        }
    }

	while (n != 0L){
		var p = 1L
		var q = 1L

		for (k in primeSet){
			if (n % k == 0L){
				p *= (k - 1)
				q *= k
			}
		}


	    println(n * p / q)
	    n = readLine()!!.toLong()
	}		
}