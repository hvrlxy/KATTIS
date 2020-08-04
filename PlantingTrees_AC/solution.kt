import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	val numTrees = sc.nextInt()

	val array = LongArray(numTrees){0L}
	for (i in 0 until numTrees){
		array[i] = sc.nextLong()
	}

	array.sortDescending()
	for (i in 0 until numTrees){
		array[i] = array[i] + (i + 1)
	}

	//println(array.joinToString())

	val max = array.max()!!

	println(max + 1L)
}