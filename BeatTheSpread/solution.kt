import java.util.Scanner
import java.io.*

fun main(args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCase = sc.nextInt()

	for (i in 0 until numCase){
		val s = sc.nextInt()
		val d = sc.nextInt()

		if ((s + d)%2 == 0){
			val a = (s + d)/2
			val b = (s - d)/2
			if (a >= 0 && b >= 0) {
				println("$a $b")
			}

			else{
				println("impossible")
			}
		}
		else{
			println("impossible")
		}
	}
}