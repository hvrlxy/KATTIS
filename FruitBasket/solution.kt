import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong() }.toMutableList() // list of ints

fun power(e: Int): Long{
	if (e == 0) return 1L
	else if (e == 1) return 2L
	else{
		val half = power(e/2)
		if (e % 2 == 0) return half * half else return half * half * 2L
	}
}

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numFruits = rd.readInt()
	val array = rd.readInts()
	var totalWeights = power(numFruits - 1) * array.sum()
	var sum = 0L

	fun find (idx: Int){
		sum += array[idx]
		if (sum >= 200L) {
			sum -= array[idx]
			return
		} 
		else {
			totalWeights -= sum
		}
		for (j in idx + 1 until array.size){
			find(j)
		}
		sum -= array[idx]
	}

	for (i in 0 until array.size){
		find(i)
	}

	println(totalWeights)
}






