import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single Int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of Ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numStudents = rd.readInt()
    val aSet = mutableSetOf<Int>()
    repeat(numStudents){
    	aSet.add(rd.readInt())
    }

    fun isValid(n: Int): Boolean{
    	val modSet = mutableSetOf<Int>()
    	for (i in aSet){
    		if ((i + n) % n in modSet) return false
    		else modSet.add((i + n) % n)
    	}
    	return true
    }

    var n = 1
    while (!isValid(n)){
    	n++
    }
    println(n)
}