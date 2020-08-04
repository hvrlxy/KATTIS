import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var caseNo = 0
    var numAnimals = rd.readInt()

    while (numAnimals > 0){
    	caseNo ++

    	var aMap = mutableMapOf<String, Int>()
    	repeat(numAnimals){
    		val aString = rd.readStrings()
    		if (aString[aString.size - 1].toLowerCase() !in aMap) aMap[aString[aString.size - 1].toLowerCase()] = 1
    		else{
    			val c = aMap[aString[aString.size - 1].toLowerCase()]!!
    			aMap[aString[aString.size - 1].toLowerCase()] = c + 1
    		}
    	}

    	val alist = aMap.keys.toMutableList()
    	alist.sort()
    	println("List $caseNo:")
    	for (i in alist){
    		println("$i | ${aMap[i]!!}")
    	}

    	numAnimals = rd.readInt()
    }
}