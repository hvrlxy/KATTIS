import java.util.Scanner
import java.io.*

fun main(args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numBuses = sc.nextInt()

	val busArray = IntArray(numBuses){0}

	for (i in 0 until numBuses){
		busArray[i] = sc.nextInt()
	}

	busArray.sort()

	
}