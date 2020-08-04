import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val stats = rd.readInts()

	val board = Array<Array<Array<Int>>>(26){Array<Array<Int>>(26){Array<Int>(26){-1}}}
	val alist = Array<String>(stats[0]){""}

	val result = StringBuilder()

	for(l in 0 until stats[0]){
		val word = rd.readLn()
		alist[l] = word

		for (i in 0 until word.length){
			for (j in i + 1 until word.length){
				for (k in j + 1 until word.length){
					if (board[word[i] - 'a'][word[j] - 'a'][word[k] - 'a'] == -1) board[word[i] - 'a'][word[j] - 'a'][word[k] - 'a'] = l
				}
			}
		}
	}

	repeat(stats[1]){
		val plate = rd.readLn().toLowerCase()
		if (board[plate[0] - 'a'][plate[1] - 'a'][plate[2] - 'a'] == -1)result.append("No valid word\n") else result.append("${alist[board[plate[0] - 'a'][plate[1] - 'a'][plate[2] - 'a']]}\n")
	}
	print(result)
}