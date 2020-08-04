import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numQuery = rd.readInt()
	repeat(numQuery){
		val k = rd.readInt()

		val r = IntArray(k){-1}
		val modFibArray = IntArray(k + 3){0}
		modFibArray[0] = 1
		modFibArray[1] = 1
		for (i in 2 until k + 3){
			modFibArray[i] = (modFibArray[i - 1] + modFibArray[i - 2]) % k
			if (r[modFibArray[i]] == -1) r[modFibArray[i]] = i
			else{
				println(r[modFibArray[i]])
				break
			}
		}
	}
}