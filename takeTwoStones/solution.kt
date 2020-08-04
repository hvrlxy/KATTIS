import java.util.Scanner
import java.io.*

fun main (args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))
	val numStone = sc.nextInt()
	if (numStone %2 == 0){
		println("Bob")
	}
	else{
		println("Alice")
	}
}