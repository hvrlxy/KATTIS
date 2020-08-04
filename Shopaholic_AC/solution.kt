import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	val numItems = sc.nextInt()

	val array = LongArray(numItems){0L}
	for (i in 0 until numItems){
		array[i] = sc.nextLong()
	}

	array.sortDescending()

	var result = 0L
	for (i in 2 until numItems step 3){
		result += array[i]
	}

	println(result)
}