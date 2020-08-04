fun main(){
	val stats = readLine()!!.split(" ").map{ it.toLong() }

	val Q = stats[0]
	val M = stats[1]
	var S = stats[2]
	var L = stats[3]

	var result = (L / M) * Q 

	L -= (L / M) * M 
	if (L > 0) result += Q 
	else if (L == 0L){
		result += (S / M)
		if (S - (S / M) * M == 0L) return println(result) else return println ("${result + 1}")
	}
	S -= (M - L) * Q
	if (S > 0) result += (S / M) else return println(result)
	S -= (S / M) * S
	if (S > 0) println(result + 1) else println(result)
}