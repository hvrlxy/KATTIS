import kotlin.math.*

fun main(){
	val aString = readLine()!!

	if (aString.length <= 4){
		val n = aString.toInt()

		fun factorial (i: Int): Int {
			if (i == 0) return 1 else return factorial(i - 1)*i
		}

		for (i in 1 until 8){
			//println(factorial(i))
			if (factorial(i) == n) return println(i)
		}
	}

	fun findFactorialDigits (n: Int): Int{
		// f(x) = n* log10(( n/ e)) + log10(2*pi*n)/2 - Kamenetsky’s formula
		return (n * log10(n / E) + log10(2 * PI * n) / 2.0).toInt() + 1
	}

	var i = 7
	while (true){
		if (findFactorialDigits(i) == aString.length) return println(i)
		i++
	}
}