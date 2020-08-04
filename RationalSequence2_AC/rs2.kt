import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))
	val numCase = sc.nextInt()

	for (i in 0 until numCase){
		val caseNo = sc.nextInt()

		val pq = sc.next()
		val b = pq.split("/")

		val aList = intArrayOf(b[0].toInt(), b[1].toInt())

		val positionList = mutableListOf<Char>()

		while ((aList[0] + aList[1] != 2)){
			if (aList[0] > aList[1]){
				positionList.add('R')
				aList[0] = aList[0] - aList[1]
			}
			else{
				positionList.add('L')
				aList[1] = aList[1] - aList[0]
			}
		}

		positionList.reverse()

		var result = 1
		for (j in 0 until positionList.size){
			if (positionList[j] == 'L'){
				result = result * 2
				//println(result)
			}
			else{
				result = result * 2 + 1
			}
		}

		println("$caseNo $result")
	}
}