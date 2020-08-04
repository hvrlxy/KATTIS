fun main(){
	var aList = readLine()!!.split(" ").map{it.toLong()}.toMutableList()
	while (aList.sum() != 0L){
		var a1 = aList[0]
		var a2 = aList[1]

		var carrier = 0
		var b = 0L
		while (a1 > 0L && a2 > 0L){
			if (((b + a1 % 10 + a2 % 10) / 10) > 0L){
				carrier ++
				b = (b + a1 % 10 + a2 % 10) / 10
			}
			a1 /= 10L
			a2 /= 10L
		}

		if (kotlin.math.max(a1, a2) % 10 + b >= 10) carrier ++

		if (carrier >= 2) println("$carrier carry operations.")
		else if (carrier == 0) println("No carry operation.")
		else println("$carrier carry operation.")

		aList = readLine()!!.split(" ").map{it.toLong()}.toMutableList()
	}
}