import kotlin.math.pow

fun formula(a:Long):Long {
    return a.toDouble().pow(1/9.toDouble()).toLong()
}

fun main(){
	val n = readLine()!!.toLong()
	val max = formula(n)
	for (i in max + 1 downTo 1){
		if (n % (i * i * i * i * i * i * i * i * i) == 0L) return println(i)
	}
}


