// be very careful, there are a lot of edge cases in this problem.

fun main(){
	//println("1234567890: ${countZeros(1234567890L)}")
	//println("2345678901: ${countZeros(2345678901L)}")
    var a = readLine()!!.split(" ").map{it.toLong()}

    while (a[0] >= 0){
        var m = a[0]
        var n = a[1]
        //println(countZeros(n))

        println(countZeros(n) - countZeros(m) + numZeros(m))
        a = readLine()!!.split(" ").map{it.toLong()}
    } 
}

fun numZeros(m: Long): Long{
	//this function counts how many zeros in the decimal representation of m
	val s = m.toString()
	var result = 0L
	for (c in s){
		if (c == '0') result ++
	}
	return result
}

fun countZeros(n: Long): Long{
	if (n == 0L) return 1L
	// this function counts how many zeros are written from 0 to that n
	var n = n
	var c = 10L
	var result = 1L // zero has one zero in it

	while (c <= n){
		// count how many number from 0 to n that is divisible by c
		val n1 = n / c * c // take the biggest number smaller than n and divisible by c
		val dC = (n1 - c) / c // count how many number from c to n1
		result += dC * (c / 10)

		//count the number of zeros from n1 to n in that spot:
		if (n1 / (c / 10) == n / (c / 10)) result += (n - n1 + 1)
		else{
			result += (c / 10)
		}
		c *= 10
	}
	return result
}