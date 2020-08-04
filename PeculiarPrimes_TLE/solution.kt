import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))
	var result = StringBuilder()
	var numPrimes = rd.readInt()
	while (numPrimes != 0){
		val primeSet = rd.readInts().toMutableSet()

		val interval = rd.readInts()

		val resultSet = mutableSetOf<Int>()
		val factorSet = mutableListOf<Int>()

		factorSet.add(1)

		while (factorSet.isNotEmpty()){
			val n = factorSet[0]
			factorSet.removeAt(0)

			if (n >= interval[0] && n <= interval[1] ) resultSet.add(n)
			for (i in primeSet){
				if (i * n <= interval[1]) factorSet.add(i*n) else break
			}
		}

		val resultList = resultSet.toMutableList()
		resultList.sort()

		if (resultSet.size >0) result.append("${resultList.joinToString(separator = ",")}\n") else result.append("none\n")
		numPrimes = rd.readInt()
	}
	print(result)
}