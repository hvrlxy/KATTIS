import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toList() // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val num = rd.readInt()

	val As = mutableListOf<Int>()
	val Bs = mutableListOf<Int>()

	repeat(num){
		val a = rd.readInts()
		As.add(a[0])
		Bs.add(a[1])

		As.sort()
		Bs.sort()

		var max = Int.MIN_VALUE
		for (i in 0 until As.size){
			val d = As[i] + Bs[Bs.size - i - 1]
			if (d > max) max = d
		}
		println(max)
	}
}