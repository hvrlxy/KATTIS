import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toList() // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val stats = rd.readInts()

	val matrix = mutableListOf<String>()

	repeat(stats[0]){
		matrix.add(rd.readLn())
	}

	var currentRow = matrix[matrix.size - 1]
	matrix.removeAt(matrix.size - 1)

	val duplicateColumns = mutableMapOf<Char, MutableList<Int>>()

	for (i in 0 until stats[1]){
		if (currentRow[i] !in duplicateColumns.keys) duplicateColumns[currentRow[i]] = mutableListOf<Int>(i)
		else duplicateColumns[currentRow[i]]!!.add(i)
	}

	var values = duplicateColumns.values.toMutableList().filter{it.size > 1}

	var count = 0
	while (values.isNotEmpty()){
		count ++
		currentRow = matrix[matrix.size - 1]
		matrix.removeAt(matrix.size - 1)

		val newValues = mutableListOf<MutableList<Int>>()

		for (alist in values){
			val aMap = mutableMapOf<Char, MutableList<Int>>()
			for (i in alist){
				if (currentRow[i] !in aMap.keys) aMap[currentRow[i]] = mutableListOf<Int>(i)
				else aMap[currentRow[i]]!!.add(i)
			}
			newValues.addAll(aMap.values.toMutableList().filter{it.size > 1})
		}
		values = newValues
	}
	println(stats[0] - (count + 1))
}













