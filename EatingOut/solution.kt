import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)
	val numItems = sc.nextInt()

	val array = IntArray(3){0}
	for (i in 0 until 3){
		array[i] = sc.nextInt()
	}

	if (array[2] > 2 * numItems - (array[1] + array[0])) return println("impossible") else println("possible")
}