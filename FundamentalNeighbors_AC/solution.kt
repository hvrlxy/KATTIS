import kotlin.math.*
import java.io.*

private fun BufferedReader.readLn() = readLine()

fun pow(b: Int, e: Int): Long{
	var pow = 1L
	var e = e
	var b = b
	while (e > 0){
		if (e % 2 == 0){
			b *= b
			e /= 2
		}
		else{
			pow *= b
			e --
		}
	}
	return pow
}

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var aString = rd.readLn()
	val booleanArray = BooleanArray(50000){true}

    for (i in 2 until 50000){
        if (booleanArray[i]){
            var j = 2
            while (i * j < 50000){
                booleanArray[i * j] = false
                j ++
            }
        }
    }

    var aMap = mutableMapOf<Int, Int>()

    val primes = mutableListOf<Int>()
    for (i in 2 until 50000){
        if (booleanArray[i]) primes.add(i)
    }

    fun factor(n: Int){
        var n = n
        for (i in primes){
            while (n % i == 0){
                if (i !in aMap.keys) aMap[i] = 1
                else {
                    val c = aMap[i]!!
                    aMap[i] = c + 1
                }
                n /= i
                if (n == 1) return
            }
        }

        if (n > 1) aMap[n] = 1
    }

    var printResult = StringBuilder()

    while (aString != null){
    	aMap = mutableMapOf<Int, Int>()

    	factor(aString.toInt())
    	var result = 1L
    	for (b in aMap.keys){
    		result *= pow(aMap[b]!!, b)
    	}
    	printResult.append("$aString $result\n")
    	aString = rd.readLn()
    }
    print(printResult)
}