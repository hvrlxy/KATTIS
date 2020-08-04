import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	val aString = sc.next()
	val array = IntArray(aString.length){0}

	for (i in 0 until aString.length){
		array[i] = aString[i].toString().toInt()
	}

	var idx = 0												//index of the wrong position

	for (i in aString.length - 1 downTo 0){
		if (i == 0){
			println(0)
			return
		}
		else if (array[i] > array[i - 1]){
			idx = i
			break
		}
	}

	val b = array.slice(idx .. array.size - 1).toMutableList()

	val smallestBigger = smallestBigger(array[idx], b) //the index of the smallest bigger interger than array[idx] in the array b

	for (i in idx until array.size){
		array[i] = b[i - idx]
	}

	val result = StringBuilder()
	for (i in 0 until array.size){
		result.append(array[i])
	}
	println(result)
}


fun smallestBigger(idx : Int, array: MutableList<Int>): Int{
	//return the index of the smallest bigger integers in the array
	val resultNum = idx
	val result = 0
	for (i in 0 until array.size){
		if (array[i] > idx){
			resultNum = array[i]
			result = i
		}
	}
	return result 
}