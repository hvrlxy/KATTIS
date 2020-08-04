import java.util.Scanner
import java.io.*

fun main(args:Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))
	val num = sc.nextInt()
	for (i in 0 until num){
		println("${i+1} Abracadabra")
	}
}
