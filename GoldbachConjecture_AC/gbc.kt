import java.util.Scanner
import java.io.*
import kotlin.math.*

val array = primeArray()

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCase = sc.nextInt()

	for (i in 0 until numCase){
		val num = sc.nextInt()

		val rList = createRepresentation(num)

		println("$num has ${rList.size} representation(s)")
		for (j in 0 until (rList.size)){
			println("${rList[j]}+${num - rList[j]}")
		}
		println()
	}
}
	
fun createRepresentation(x: Int): MutableList<Int> {
	val rList = mutableListOf<Int>()

	for (i in 0 .. (x / 2)){
		if (array[i]){
			if (array[x - i]){
				rList.add(i)
			}
		}
	}
	return rList
}

fun primeArray(): BooleanArray {
	//create an array of prime number marked by true and false
	val a = BooleanArray(32001){true}

	a[0] = false
	a[1] = false

	var i = 2
	while (i*i <= 32000){
		if (a[i]) {
			for (k in (i+i) until 32001 step i){
				a[k] = false
			}
		}
		i ++
	}
	return a
}
