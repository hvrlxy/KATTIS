import java.util.Scanner
import java.io.* 

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numGuests = sc.nextInt()
	val aMap = mutableMapOf<Int, Int>()
	var result = numGuests

	for (i in 0 until numGuests){
		val language = sc.nextInt()
		if (aMap.containsKey(language!!) && (i - aMap[language]!! < result)){
			result = i - aMap[language]!!
			aMap[language] = i
		}
		else{
			aMap[language] = i
		}
	}

	println(result)
}