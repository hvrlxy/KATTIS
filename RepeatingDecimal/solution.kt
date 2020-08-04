fun main(){
	var aline = readLine()
	while (aline != null){
		val s = aline.split(" ").map{it.toInt()}

		var a = s[0]
		val b = s[1]
		var numDigits = s[2]

		val result = StringBuilder("0.")
		while (numDigits > 0){
			a *= 10
			//println(a)
			result.append("${a/b}")
			a = a - (a/b) * b
			numDigits --
		}
		println(result)
		aline = readLine()
	}
}