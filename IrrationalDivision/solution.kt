fun main(){
	val a = readLine()!!.split(" ").map{it.toInt()}

	val r = a[0]
	val c = a[1]

	//check for edge case first

	if (r == 1 && c % 2 == 0) return println(2)
	if (r == 1 && c % 2 == 1) return println(1)
	if (r % 2 == 0 && c == 1) return println(0)
	if (r % 2 == 0 && c == 1) return println(0)

	//check for other case

	if (r % 2 == 1 && c % 2 == 0) {
		if (r > c) return println(0) else return println(2)
	}
	if (r % 2 == 1 && c % 2 == 1) return println(1)
	if (r % 2 == 0 && c % 2 == 0) return println(0)
	if (r % 2 == 0 && c % 2 == 1) return println(0)
}