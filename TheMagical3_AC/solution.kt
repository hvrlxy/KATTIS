//import java.io.*
// dont even need sieve for this problem if you use C++

fun main(){
	var n = readLine()!!.toInt()

	fun findSmallestPrime(n: Int): Int{
		var i = 2
		while (i * i <= n){
			if (n % i == 0) return i
				i++
		}
		return n
	}

	fun solve(n: Int): Int{
		if (n <= 6 && n != 3) return -1
		for (i in 4 until 10){
			if (n % i == 3) return i
		}
		val p = findSmallestPrime(n - 3)
		if (p <= 3) return findSmallestPrime((n - 3) / p)
		else return p
	}
	val print = StringBuilder()

	while (n != 0){
		val result = solve(n)
		if (result == -1) print.append("No such base\n") else print.append("$result\n")
			
		n = readLine()!!.toInt()
	}
	print(print)
}