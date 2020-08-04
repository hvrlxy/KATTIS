import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val num = sc.nextInt()

	val aMap = mutableMapOf<String, Array<String>>()
	val array = Array<String>(num){""}

	for (i in 0 until num){
		array[i] = sc.next()
		val numWords = sc.nextInt()
		aMap[array[i]] = Array<String>(numWords){sc.next()}
	}

	val resultArray = mutableListOf<String>()

	val occurenceArray = IntArray(num){0}

	while (sc.hasNext()){
		if (resultArray.size == num){
			break
		}

		val word = sc.next()
		for (i in 0 until array.size){
			for (j in aMap[array[i]]!!){
				if (j == word){
					occurenceArray[i] ++
				}
			}
		}
	}

	val max = occurenceArray.max()

	for (i in 0 until array.size){
		if (occurenceArray[i] == max){
			resultArray.add(array[i])
		}
	}

	resultArray.sort()
	for (i in 0 until resultArray.size){
		println(resultArray[i])
	}
}