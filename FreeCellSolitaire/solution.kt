fun main(){
	val a = readLine()!!.split(" ").map{it.toInt()}

	val N = a[0]
	val M = a[1]
	val K = a[2]

	var total = N.toLong()

	for (m in 0 .. M){
		total += (N + 1) * m
	}

	if (K - 1 <= total) println("yes") else println("no")
}