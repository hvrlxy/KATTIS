fun main(){
	val a = readLine()!!.split(" ").map{it.toInt()}

	val n = a[0]
	val r = a[1]
	val k = a[2]

	if (k >= r){
		if (n < 2 * k - r) return println(2 * k)
		else if ((n - 2* k + r) % 2 == 0) return println(n + r)
		else return println(n + r + 1)
	}
	else{
		if (n < r) return println(2 * r)
		else if ((n - r) % 2 == 0) return println(n + r)
		else return println(n + r + 1)
	}
}