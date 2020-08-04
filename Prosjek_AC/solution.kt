fun modLinearEquation (a: Long, b: Long, n: Long): Long{
	val d = extendedEuclid(a,n)
	if (b % d.first == 0L){
		val x0 = d.second * (b / d.first) % n
		return (x0 + 1 * (n / d.first))
	}
	else return -1L
}

fun extendedEuclid (a: Long, b: Long): Triple<Long, Long, Long>{
	if (b == 0L) return (Triple(a,1L,0L))
	val dPrime = extendedEuclid(b, a%b)
	return Triple(dPrime.first, dPrime.third, dPrime.second - dPrime.third * (a/b))
}

fun main(){
	val aString = readLine()!!
	val MOD = "1${"0".repeat(aString.length - 2)}".toLong()

	val n = (aString.toDouble() * MOD).toLong()

	var numPapers = modLinearEquation(n,0,MOD)
	//println(numPapers)
	var total = numPapers * n / MOD
	//println(total)

	var num1s = numPapers
	var num2s = 0L
	var num3s = 0L
	var num4s = 0L
	var num5s = 0L

	 while(total > num1s * 1 + num5s * 5){
        num1s --
        num5s ++
    }

	if (num1s * 1 + num5s * 5 - total == 1L){
		num5s --
		num4s ++
	}
	else if (num1s * 1 + num5s * 5 - total == 2L){
		num5s --
		num3s ++
	}
	else if (num1s * 1 + num5s * 5 - total == 3L){
		num5s --
		num2s ++
	}

	println("$num1s $num2s $num3s $num4s $num5s")
}