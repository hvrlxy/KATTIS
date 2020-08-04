fun main(){
	var num1 = readLine()

	fun findWinner(n: Long): Long{
		var result = 0L
		var num = n
		while (num > 1){
			if (num % 9L == 0L) num /= 9L else num = (num + (9L - num % 9L)) / 9L
			result ++
			if (num > 1){
				num = (num + num % 2L) / 2L
				result ++
			}
			else break
		}
		return result
	}

	while (num1 != null){
		val n = num1.toLong()
		if (findWinner(n) % 2 == 0L) println("Ollie wins.") else println("Stan wins.")
		num1 = readLine()
	}
}