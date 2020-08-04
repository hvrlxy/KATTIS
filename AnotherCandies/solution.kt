import java.util.Scanner
import java.io.*

fun main (args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCase = sc.nextInt()

	for (i in 0 until numCase){
		val numPeople = sc.nextInt()

		var sumCandies = 0L
		for (j in 0 until numPeople){
			sumCandies += sc.nextLong() % numPeople
		}

		if (sumCandies % numPeople != 0L){
			println("NO")
		}
		else{
			println("YES")
		}
	}
}