import java.util.Scanner
import java.io.*
import kotlin.math.abs

fun main (args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))
	while (sc.hasNext()){
		val x: Long = (sc.next()).toLong()
		val y: Long = (sc.next()).toLong()

		println(abs(x - y))
	}
}