import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	val A = sc.nextInt()
	val B = sc.nextInt()
	val C = sc.nextInt()

	val array = Array<Pair<Int, Int>>(3){sc.nextInt() to sc.nextInt()}

	array.sortBy{ it.second }
	val max = array[2].second
	array.sortBy{ it.first }

	var result = 0
	for (i in array[0].first until max){
		if (i in array[0].first until array[0].second && i in array[1].first until array[1].second && i in array[2].first until array[2].second) {
			result += C * 3
			//println("3: $i")
		}
		else if ((i in array[0].first until array[0].second && i in array[1].first until array[1].second)
			|| (i in array[1].first until array[1].second && i in array[2].first until array[2].second)
			|| (i in array[2].first until array[2].second && i in array[0].first until array[0].second)) {
			result += B * 2
			//println("2: $i")
		}
		else if( i in array[0].first until array[0].second || i in array[1].first until array[1].second || i in array[2].first until array[2].second ){
			result += A
			//println("1: $i")
		}
	}

	println(result)
}