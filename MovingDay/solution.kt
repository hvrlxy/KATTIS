import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)
	val numBoxes = sc.nextLong()
	val d = sc.nextLong()

	var max = Long.MIN_VALUE
	while (sc.hasNext()){
		val volumn = sc.nextLong() * sc.nextLong() * sc.nextLong()
		if (volumn > max) max = volumn
	}
	println(max - d)
	// val a = readLine()!!.split(" ").map{ it.toInt() }
	// var max = Int.MIN_VALUE
	// repeat(a[0]){
	// 	val array = readLine()!!.split(" ").map{ it.toInt() }
	// 	if (array[0] * array[1] * array[2] > max) max = array[0] * array[1] * array[2]
	// }
	// println(max - a[1])
}