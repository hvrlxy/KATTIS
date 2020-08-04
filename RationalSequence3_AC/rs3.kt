import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCase = sc.nextInt()

	for (i in 0 until numCase){
		val caseNo = sc.nextInt()

		var position = sc.nextInt()

		var p = 1 	//numerator
		var q = 1 	//denominator

		var positionArray = mutableListOf<Char>()

		while (position != 1){
			if (position % 2 == 0){
				position /= 2
				positionArray.add('L')
			}
			else{
				position = (position - 1)/2
				positionArray.add('R')
			}
		}

		positionArray.reverse()
		//println(positionArray.joinToString())

		for (j in 0 until positionArray.size){
			if (positionArray[j] == 'L'){
				q = p + q
			}
			else{
				p = p + q
			}
		}

		println("$caseNo $p/$q")
	}
}