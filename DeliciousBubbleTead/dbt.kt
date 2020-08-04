import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)
	val numTea = sc.nextInt()

	val teaArray = IntArray(numTea){0}

	for (i in 0 until numTea){
		teaArray[i] = sc.nextInt()
	}

	val topping = sc.nextInt()
	val toppingArray = IntArray(topping){0}

	for (i in 0 until topping){
		toppingArray[i] = sc.nextInt()
	}

	var min = Int.MAX_VALUE
	for (i in 0 until numTea){
		val total = sc.nextInt()
		for (j in 0 until total){
			val num = sc.nextInt() - 1
			if(toppingArray[num] + teaArray[i] < min){
				min = toppingArray[num] + teaArray[i]
			}
		}
	}
	//println(min)
	println(sc.nextInt() / min - 1)
}