import java.io.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var aString = rd.readLn()
    while (aString != null){
    	val numList = aString.split(" ").map{ it.toInt() }.toIntArray()

    	for (i in numList){
    		if (i == numList.sum() - i) {
    			println(i)
    			break
    		}
    	}
    	aString = rd.readLn()
    }
}