fun main(){
	val knightsPos = mutableListOf<Pair<Int, Int>>()

	for (i in 0 until 5){
		val aString = readLine()!!
		for (j in 0 until 5){
			if (aString[j] == 'k') knightsPos.add(i to j)
		}
	}

	if (knightsPos.size != 9) return println("invalid")

	fun checkValid(p: Pair<Int, Int>): Boolean{
		if ((p.first - 1 to p.second - 2) in knightsPos) return false
		if ((p.first - 1 to p.second + 2) in knightsPos) return false
		if ((p.first + 1 to p.second - 2) in knightsPos) return false
		if ((p.first + 1 to p.second + 2) in knightsPos) return false
		if ((p.first - 2 to p.second - 1) in knightsPos) return false
		if ((p.first - 2 to p.second + 1) in knightsPos) return false
		if ((p.first + 2 to p.second - 1) in knightsPos) return false
		if ((p.first + 2 to p.second + 1) in knightsPos) return false
		return true
	}

	for (i in knightsPos){
		if (!checkValid(i)) return println("invalid")
	}
	println("valid")
}