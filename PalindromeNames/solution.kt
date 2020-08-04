fun main(){
	val aString = readLine()!!

	var result = Int.MAX_VALUE
	for (i in 0 .. aString.length){
		val newString = aString + "?".repeat(i)
		var changes = i
		//println(i)
		for (j in 0 until newString.length / 2){
			if (newString[j] != '?' && newString[newString.length - j - 1] != '?' && newString[j] != newString[newString.length - j - 1]) changes ++
			//println("${newString[j]} ${newString[newString.length - j - 1]}")
		}
		if (changes < result) result = changes

		//println()
	}
	println(result)
}