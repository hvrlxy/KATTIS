import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)
	val aMap = mutableMapOf<String, String>()
	val numRules = sc.nextInt()
	val numIterations = sc.nextInt()
	repeat(numRules){
		val key = sc.next()
		sc.next()
		val value = sc.next()
		aMap[key!!] = value
	}

	var first = sc.next()
	var result = StringBuilder()
	repeat(numIterations){
		for (i in 0 until first.length){
			if (first[i].toString() in aMap.keys) result.append(aMap[first[i].toString()])
			else result.append(first[i].toString())
		}
		first = result.toString()
		result = StringBuilder()
	}

	println(first)
}