import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(System.`in`)

	val s = sc.nextLong()
	val v1 = sc.nextLong()
	val v2 = sc.nextLong()

	if (v2 == 0L){
		if (s % v1 == 0L){
			println("${s/v1} 0")
		}
		else{
			println("Impossible")
		}
		return
	}

	for (i in s/v1 downTo 0){
		val volumnLeft = s - i * v1
		if ( volumnLeft % v2 == 0L){
			println("${i} ${volumnLeft / v2}")
			return
		}
	}

	println("Impossible")
}