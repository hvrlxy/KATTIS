import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toList() // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	fun mergeSort(A: IntArray, p: Int, r: Int): Long{
		fun merge(A: IntArray, p: Int, q: Int, r: Int): Long{
			var result = 0L

			var n1 = q - p + 1
			var n2 = r - q

			val L = IntArray(n1 + 1){ Int.MAX_VALUE }
			val R = IntArray(n2 + 1){ Int.MAX_VALUE }

			for (i in 0 until n1){
				L[i] = A[p + i]
			}

			for (i in 0 until n2){
				R[i] = A[q + i + 1]
			}

			var i = 0
			var j = 0
			for (k in p .. r){
				if (L[i] <= R[j]) {
					A[k] = L[i]
					n1 --
					i ++
				}
				else{
					A[k] = R[j]
					n2 --
					j ++
					result += n1
				}
			}
			return result
		}

		if (p >= r) return 0L
		val q = (p + r) / 2
		return mergeSort(A, p, q) + mergeSort(A, q + 1, r) + merge(A, p, q, r)
	}

	val n = rd.readInt()
	val A = IntArray(n){ rd.readInt() }
	println(mergeSort(A, 0, n - 1))
}