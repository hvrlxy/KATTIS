fun main(){
	var num = readLine()!!.toInt()
	while (num != -1){
		println(f(num))
		num = readLine()!!.toInt()
	}
}

fun f(n: Int): Int{
	//return the number of way the rectangle of size 3n can be formed by domino
	if (n == 0) return 1
	if (n == 1) return 0
	return (f(n - 2) + g(n - 1) * 2)
}

fun g(n: Int): Int{
	if (n == 0) return 0
	if (n == 1) return 1
	return (g(n - 2) + f(n - 1))
}