import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun gcd(x: Int, y: Int): Int{
    if (x == 0) return y
    return gcd(y.rem(x), x) 
}

fun fromCelsius (x: Int, p: Pair<Int, Int>): Int{
	return (p.first * x + p.second)	
}

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val stat = rd.readInts()
	val convertToCelsiusArray = Array<Pair<Int, Int>>(3){0 to 0}
	for (i in 0 until stat[0]){
		val a = rd.readInts()
		convertToCelsiusArray[i] = (a[0] * 100 to (a[1] - b))
	}

	repeat(stat[1]){
		val a = rd.readInts()

		val x = convertToCelsiusArray[a[0] - 1] * a[2]
		val y = convertToCelsiusArray[a[1] - 1]
		println("${x / gcd(x,y)}/${y / gcd(x,y)}")
	}
}