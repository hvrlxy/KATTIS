fun main(){
	var n = readLine()!!.toLong()

	var result = 0L

	while (n != 1L){
		result += n
		if (n % 2L == 0L){
			n = n / 2L
		}
		else if (n % 2L == 1L){
			n = n * 3L + 1L
		}
	}

	println(result + 1L)
}