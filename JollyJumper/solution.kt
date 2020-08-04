import kotlin.math.abs

fun main(){
	var a = readLine()
	while (a != null){
		var array = a.split(" ").map{ it.toInt() }.toMutableList()
		val n = array[0]
		array.removeAt(0)

		val checkArray = BooleanArray(n){false}

		var isJolly = true
		for (i in 0 until n - 1){
			if (abs(array[i] - array[i + 1]) >= n || abs(array[i] - array[i + 1]) == 0) {
				println("Not jolly")
				isJolly = false
				break

			}
			else if (checkArray[abs(array[i] - array[i + 1])]) {
				isJolly = false
				println("Not jolly")
				break
			}
			else{
				checkArray[abs(array[i] - array[i + 1])] = true
			}
		}

		if (isJolly) println("Jolly")
		a = readLine()
	}
}