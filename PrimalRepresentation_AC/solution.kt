import java.io.*

private fun BufferedReader.readLn() = readLine() // string line

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

    val primes = mutableListOf<Int>()
    for (i in 2 until 50000){
        if (booleanArray[i]) primes.add(i)
    }

    val printResult = StringBuilder()

    while (aString != null){
    	var n = aString.toInt()

    	if (n < 0){
    		printResult.append("-1 ")
    		n = -n
    	}

    	fun factor(n: Int){
	        var n = n
	        for (i in primes){
	        	if (n % i == 0) {
	        		printResult.append("$i")
	        		var pow = 0
	            	while (n % i == 0){
	            		pow ++
	                	n /= i
	            	}
	            	if (pow > 1) printResult.append("^$pow")
	            	printResult.append(" ")

	            	if (n == 1) return
	        	}	
	        }

	        if (n > 1) printResult.append("$n ")
	    }

    	factor(n)
    	printResult.deleteCharAt(printResult.length - 1)
    	printResult.append("\n")
    	aString = rd.readLn()
    }
    print(printResult)
}