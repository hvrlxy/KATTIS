import java.util.Scanner
import java.io.*
import kotlin.math.*

fun main (args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCase = sc.nextInt()

	for (i in 0 until numCase){
		val aString = sc.next()
		val aList = aString.split(",")

		var result = 0L
		var j = 0

		for (i in aList.size - 1 downTo 0){
			if (aList[i] != ""){
				result += (aList[i].toLong() * 60.0.pow(j)).toLong()
			}
			j ++
		}

		println(result)
	}
}