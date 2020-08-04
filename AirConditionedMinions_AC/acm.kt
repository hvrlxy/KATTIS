import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numMinions = sc.nextInt()

	val array = Array<Minion>(numMinions) {Minion(sc.nextInt(), sc.nextInt())}

	array.sortBy {it.upperbound}

	var result = 0
	var temp = Int.MIN_VALUE

	for (i in 0 until numMinions){
		if (array[i].lowerbound > temp){
			result ++
			temp = array[i].upperbound
		}
	}

	println(result)
}

data class Minion(val lowerbound: Int, val upperbound: Int)