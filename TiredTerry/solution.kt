import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a = rd.readInts()
    val aString = rd.readLn()
    val array = IntArray(a[0]){0}
    for (i in 0 until a[0]){
    	if (aString[i] == 'Z') array[i] = 1
    }
    
    var result = 0

	var d = 0
	for (j in 0 until a[1]){
		var idx = (0 - j + a[0]) % a[0]
		d += array[idx]
	}
	if (d < a[2]) result++

	for (i in 1 until a[0]){
		d += array[i]
		d -= array[(i - a[1] + a[0]) % a[0]]
		if (d < a[2]) result++
	}

    println(result)
}