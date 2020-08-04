import java.io.*

private fun BufferedReader.readLn() = readLine()

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))
	
	var aString = rd.readLn()
	val result = StringBuilder()
	while (aString != null){
		var isZero = true
		for (k in aString.length - 1 downTo 0){
			val aMap = mutableMapOf<String, Int>()
			for (j in 0 .. aString.length - k){
				val subString = aString.substring(j .. j + k - 1)
				if (subString !in aMap) aMap[subString] = 1
				else{
					val c = aMap[subString]!!
					aMap[subString] = c + 1
				}
			}

			val values = aMap.values.toMutableList().filter{it < 2}
			if (values.isEmpty()){
				result.append("$k\n")
				isZero = false
				break
			}
			
		}

		if (isZero) result.append("0\n")
		aString = rd.readLn()
	}
	print(result)
}