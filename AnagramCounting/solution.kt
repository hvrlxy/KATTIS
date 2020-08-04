import java.math.BigInteger

fun main (){
	var aString = readLine()
	while (aString != null){
		val aMap = mutableMapOf<Char,Int>()
		for (c in aString){
			if (aMap.containsKey(c)){
				aMap[c] = aMap[c]!! + 1
			}
			else{
				aMap[c] = 1
			}
		}
		var permutation = factorial(aString.length)
		for (i in aMap.values){
			permutation = permutation.div(factorial(i))
		}
		println(permutation)
		aString = readLine()
	}
}

fun factorial(x: Int): BigInteger{
	var result = BigInteger.ONE
	for (i in 1 .. x){
		result = result.times(i.toBigInteger())
	}
	return result
}