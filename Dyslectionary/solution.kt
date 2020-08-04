import java.util.Scanner
import java.io.*


fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	var array = mutableListOf<String>()	

	while (sc.hasNext()){
		val string = sc.nextLine()
		if (string != ""){
			array.add(string)
		}

		else{
			for (i in 0 until array.size){
				array[i] = array[i].reversed()
			}

			array.sort()

			var maxLength = 0

			for (i in 0 until array.size){
				if (array[i].length > maxLength){
					maxLength = array[i].length
				}
			}

			for (i in 0 until array.size){
				array[i] += " ".repeat(maxLength - array[i].length)
			}

			for (i in 0 until array.size){
				println(array[i].reversed())
			}

			println()
			array = mutableListOf<String>()
		}
	}

	for (i in 0 until array.size){
		array[i] = array[i].reversed()
	}

	array.sort()

	var maxLength = 0

	for (i in 0 until array.size){
		if (array[i].length > maxLength){
			maxLength = array[i].length
		}
	}

	for (i in 0 until array.size){
		array[i] += " ".repeat(maxLength - array[i].length)
	}

	for (i in 0 until array.size){
		println(array[i].reversed())
	}
}