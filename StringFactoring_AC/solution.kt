fun main(){
	var s = readLine()!!
	//while(s != "*"){
		println(stringFactor(s, s.length))
	//	s = readLine()!!
	//}
}

fun stringFactor(s: String, d: Int): Int{
	if (s == "") return 0
	if (d <= 0) return s.length

	for (i in 0 until s.length - d){
		val substring = s.substring(i, i + d)
		//println(substring)
		var k = i + d
		while (k + d <= s.length && s.substring(k, k + d) == substring){
			//println(s.substring(k, k + d))
			//println(k)
			k = k + d
		}
		if (k == i + d) continue
		else{
			//println("1 : stringFactor(${s.substring(0, i)}, $i) : ${stringFactor(s.substring(0, i), i)}")
			//println("2 : stringFactor($substring, $d) : ${stringFactor(substring, d)}")
			//println("3 : stringFactor(${s.substring(kotlin.math.min(k, s.length))}, ${s.length - k}) : ${stringFactor(s.substring(kotlin.math.min(k, s.length)), s.length - k)}")
			val mid = stringFactor(substring, d)
			if (mid == 1 && s.substring(kotlin.math.min(k, s.length)) == substring[0].toString()) return stringFactor(s.substring(0, i), i) + mid
			return stringFactor(s.substring(0, i), i) + mid + stringFactor(s.substring(kotlin.math.min(k, s.length)), s.length - k)
		}
	}

	return stringFactor(s, d - 1)
}