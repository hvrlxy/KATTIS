import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var x0 = rd.readLn()
	while (x0 != "END"){
		if (x0 == "1") println("1")
		else{
			var count = 1

			var x = x0.length
			while (true){
				count ++
				val nextX = x.toString().length
				if (nextX == x) {
					println(count)
					break
				}
				x = nextX
			}
		}
		x0 = rd.readLn()
	}
}