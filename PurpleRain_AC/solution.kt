fun main(){
    val aline = readLine()!!

    val a = IntArray(aline.length){0}
    val b = IntArray(aline.length){0}

    for (i in 0 until aline.length){
    	if (aline[i] == 'R') {
    		a[i] = 1
    		b[i] = -1
    	} 
    	else {
    		a[i] = -1
    		b[i] = 1
    	}
    }

    fun findMax(a: IntArray): Triple<Int, Int, Int>{
    	var s = 0
    	var e = 0

    	var S = 0
    	var E = 0

    	var max_so_far = a[0]
    	var max_end_here = a[0]

    	for (i in 1 until aline.length){
    		max_end_here = max_end_here + a[i]
    		//println("$i $max_end_here")
    		if (max_end_here < 0){
    			max_end_here = 0
    			S = i + 1
    			E = i + 1
    		}
    		else{
    			E = i
    		}

    		if (max_so_far < max_end_here){
    			s = S
    			e = E
    			max_so_far = max_end_here
    		}
    	}

    	return Triple(max_so_far, s + 1, e + 1)
    }

    val resultA = findMax(a)
    val resultB = findMax(b)

    // println(a.joinToString())
    // println(resultA)
    // println(b.joinToString())
    // println(resultB)

    if (resultA.first > resultB.first){
    	println("${resultA.second} ${resultA.third}")
    }
    else if (resultA.first < resultB.first){
    	println("${resultB.second} ${resultB.third}")
    }
    else{
    	if (resultA.second < resultB.second){
    		println("${resultA.second} ${resultA.third}")
    	}
    	else if (resultA.second > resultB.second){
    		println("${resultB.second} ${resultB.third}")
    	}
    	else{
    		if (resultA.third < resultB.third){
    			println("${resultA.second} ${resultA.third}")
    		}
    		else println("${resultB.second} ${resultB.third}")
    	}
    }
}