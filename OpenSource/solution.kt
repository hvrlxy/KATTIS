import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var aString = rd.readLn()
    while (aString != "0"){
    	val aMap = mutableMapOf<String, Int>()
    	var clubList = mutableListOf<Pair<String, Int>>()
    	while (aString != "1"){
    		if (aString == "0") return
    		if (aString == aString.toUpperCase()){
    			clubList.add(aString to 0)
    		}
    		else{
    			if (aString !in aMap){
    				aMap[aString] = clubList.size - 1
    				val newPair = clubList[clubList.size - 1].copy(second = clubList[clubList.size - 1].second + 1)
    				clubList.removeAt(clubList.size - 1)
    				clubList.add(newPair)
    			}
    			else if (aMap[aString]!! != clubList.size - 1) {
    				if (aMap[aString]!! != Int.MAX_VALUE){
	    				val newPair = clubList[aMap[aString]!!].copy(second = clubList[aMap[aString]!!].second - 1)
	    				clubList.removeAt(aMap[aString]!!)
	    				clubList.add(aMap[aString]!!, newPair)
	    				aMap[aString] = Int.MAX_VALUE
    				}
    			}
    		}

    		aString = rd.readLn()
    		if (aString == "0") return
    	}

    	clubList.sortBy{it.first}
    	clubList.reverse()
    	clubList.sortBy{it.second}
    	for (i in clubList.size - 1 downTo 0){
    		println("${clubList[i].first} ${clubList[i].second}")
    	}
    	aString = rd.readLn()
    }
}