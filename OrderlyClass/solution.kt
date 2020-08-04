fun main(){
	val aString = readLine()!!
	val bString = readLine()!!

	var s = 0
	var e = aString.length - 1

	while (aString[s] == bString[s]){
		s ++
	}
	while (aString[e] == bString[e]){
		e --
	}

	for (c in 0 .. e - s){
		//println("${aString[s + c]} = ${bString[e - c]}")
		if (aString[s + c] != bString[e - c]) return println(0)
	}

	var count = 1

	//println(kotlin.math.min(s, aString.length - 1 - e))

	for (i in 1 .. kotlin.math.min(s, aString.length - 1 - e)){
		//println("${aString[s - i]} == ${aString[e + i]}")
		if (aString[s - i] == aString[e + i]) count ++
		else break
	}
	println(count)
}