fun main(){
	val aString = readLine()!!

	for (k in 1 .. aString.length - 1){
		if (aString.length % k == 0){
			val numStrings = aString.length / k
			var isValid = true
			var start = 0
			var lastString = aString.substring(start * k .. (start + 1) * k - 1)
			for (i in 1 .. numStrings - 1){
				start ++
				val currentString = aString.substring(start * k .. (start + 1) * k - 1)
				if (lastString[lastString.length - 1] + lastString.dropLast(1) != currentString) {
					//if (k == 3) println("${currentString[currentString.length - 1]} + ${currentString.dropLast(1)} $currentString $lastString")
					isValid = false
					break
				}
				lastString = currentString
			}
			if (isValid) return println(k)
		}
	}

	println(aString.length)
}