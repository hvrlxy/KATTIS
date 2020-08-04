import java.util.Scanner
import java.io.*
import kotlin.math.min

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numTests = sc.nextInt()

	val kattisMap = mutableMapOf<String, Int>()
	val DOMMap = mutableMapOf<String, Int>()

	val resultSet = mutableSetOf<String>()

	for (i in 0 until numTests){
		val result = sc.next()
		if(result !in kattisMap){
			kattisMap[result] = 1
		}
		else{
			kattisMap[result] = kattisMap.getOrDefault(result, 0)
		}

		if(result !in resultSet){
			resultSet.add(result)
		}
	}

	for (i in 0 until numTests){
		val result = sc.next()
		if(result !in DOMMap){
			DOMMap[result] = 1
		}
		else{
			var current : Int = DOMMap[result]!!
			current++
			DOMMap[result] = current
		}
		if(result !in resultSet){
			resultSet.add(result)
		}
	}

	var count = 0

	for (i in resultSet){
		if (i !in kattisMap){
			kattisMap[i] = 0
		}
		if (i !in DOMMap){
			DOMMap[i] = 0
		}

		count += min(kattisMap[i]!!, DOMMap[i]!!)
	}

	println(count)

	// println(kattisMap)
	// println(DOMMap)
	// println(resultSet)
}







