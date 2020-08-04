fun main(){
	val a = readLine()!!.split(" ").map{it.toInt()}

	val k = a[0]
	val m = a[1]
	val n = a[2]

	val alpha = (k - (m - 1)) / (n + m)

	var isP = true
	var p = m - 1 + alpha * (n + m)
	//println(p)
	while (p < k){
		if (isP){
			p += n
			isP = false
		}
		else{
			p += m
			isP = true
		}
	}

	if (isP) println("Barb") else println("Alex")
}