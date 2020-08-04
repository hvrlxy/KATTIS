import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()

	fun nChoose2(n: Int): Int = n * (n - 1) / 2

	repeat(numCase){
		var result =0
		val a = rd.readInts()
		val d = a[0]

		val sequence = rd.readInts()

		val modList = IntArray(d){0}

		var sum = 0L
		for (i in 0 until sequence.size){
			sum += sequence[i]
			if (sum % d == 0L) result++

			var m = (sum % d).toInt()
			if (m < 0) m += d
			modList[m] ++
		}

		for (i in modList) {
			result += nChoose2(i)
		}

		println(result)
	}
}