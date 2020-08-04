fun main(){
	val n = readLine()!!.toLong()
	val largestDivisor : Long
	for (i in 2 .. kotlin.math.sqrt(n.toDouble()).toInt()){
		if (n%i == 0L){
			largestDivisor = n/i
			return println(n - largestDivisor)
		}
	}
	return println(n - 1)
}