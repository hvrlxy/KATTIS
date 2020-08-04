import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()
	repeat(numCase){
		val a1 = rd.readInts().toMutableList<Int>()
		a1.removeAt(0)

		fun isArithmetic(a: MutableList<Int>): Boolean{
			//given an ordered list of integers, return true if the sequence is arithmetic
			val distance = a[1] - a[0]
			for (i in 1 until a.size - 1){
				if (a[i + 1] - a[i] != distance) return false
			}
			return true
		}
		
		if (isArithmetic(a1)) println("arithmetic")
		else{
			a1.sort()
			if (isArithmetic(a1)) println("permuted arithmetic")
			else println("non-arithmetic")
		}
	}
}