import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCase = sc.nextInt()
	for (i in 0 until numCase){
		val caseNo = sc.nextInt()
		val numAmount = sc.nextInt()
		val array = IntArray(numAmount){0}
		for (i in 0 until numAmount){
			array[i] = sc.nextInt()
		}

		val arraySorted = array.sorted()
		var count = 0
		var j = 0
		for (i in 0 until array.size){
			if (array[i] == arraySorted[j]){
				j++
				count++
			}
		}
		println("$caseNo ${numAmount - count}")
	}
}