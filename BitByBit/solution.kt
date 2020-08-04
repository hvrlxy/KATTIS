import java.util.Scanner
import java.io.*
import kotlin.math.*

var bitArray = DoubleArray(32){0.5}

fun main (args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))
	var numOperations = sc.nextInt()

	while (numOperations != 0){
		for (i in 0 until numOperations){
			val line = sc.next()
			if (line == "SET"){
				SET(sc.nextInt())
			}
			else if (line == "CLEAR"){
				CLEAR(sc.nextInt())
			}
			else if (line == "AND"){
				AND(sc.nextInt(), sc.nextInt())
			}
			else{
				OR(sc.nextInt(), sc.nextInt())
			}
		}
		var aString = ""
		for (i in 0 .. 31){
			if (bitArray[i] == 0.5){
				aString += "?"
			}
			else if (bitArray[i] == 1.0){
				aString += "1"
			}
			else{
				aString += "0"
			}
		}
		println(aString.reversed())
		bitArray = DoubleArray(32){0.5}

		numOperations = sc.nextInt()
	}
}

fun SET(x: Int){
	bitArray[x] = 1.0
}

fun CLEAR(x: Int){
	bitArray[x] = 0.0
}

fun AND(x: Int, y: Int){
	bitArray[x] = min(bitArray[x], bitArray[y])
}

fun OR(x: Int, y: Int){
	bitArray[x] = max(bitArray[x], bitArray[y])
}