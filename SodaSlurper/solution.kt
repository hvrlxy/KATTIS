fun main(){
	val a = readLine()!!.split(" ").map{ it.toInt() }

	var totalSoda = a[0] + a[1]
	var result = 0
	while (totalSoda >= a[2]){
		val emptyBottles = totalSoda/a[2]
		result += totalSoda / a[2]
		totalSoda = totalSoda - a[2] * emptyBottles + emptyBottles
	}
	println(result)
}