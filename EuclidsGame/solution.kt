fun main(){
	var aString = readLine()!!.split(" ").map{it.toInt()}
	var a = kotlin.math.max(aString[0], aString[1])
	var b = aString[0] + aString[1] - a 
	while (a + b != 0){
		val modSet = mutableListOf<Int>()
		var numStates = 0
		fun gcd(a: Int, b: Int){
			numStates ++
			if (b == 0) return
			modSet.add(a/b)
			gcd(b, a - b * (a/b))
		}

		gcd(a,b)

		val state = Array<Int>(numStates){-1}

		state[0] = 0
		modSet.reverse()

		for (i in 0 until numStates - 1){
			if (state[i] == 0) {
				state[i + 1] = 1
				modSet.removeAt(0)
			}
			else if (state[i] == 1 && modSet[0] == 1){
				state[i + 1] = 0
				modSet.removeAt(0)
			}
			else if (state[i] == 1 && modSet[0] > 1){
				state[i + 1] = 1
				modSet.removeAt(0)
			}
		}

		if (state[numStates - 1] == 0) println("Ollie wins") else println("Stan wins")

		aString = readLine()!!.split(" ").map{it.toInt()}
		a = kotlin.math.max(aString[0], aString[1])
		b = aString[0] + aString[1] - a
	}
}