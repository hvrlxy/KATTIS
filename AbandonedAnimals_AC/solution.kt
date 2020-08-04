import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val N = rd.readInt()
    val K = rd.readInt()

    val aMap = mutableMapOf<String, MutableList<Int>>()

    repeat(K){
    	val aline = rd.readStrings()
    	if (aline[1] !in aMap.keys) aMap[aline[1]] = mutableListOf(aline[0].toInt())
    	else aMap[aline[1]]!!.add(aline[0].toInt())
    }

    val M = rd.readInt()

    val configuration1 = IntArray(M){-1}
    val configuration2 = IntArray(M){-1}

    val goods = Array<String>(M){rd.readLn()}

    for (s in 0 until M){
    	aMap[goods[s]]!!.sort()
    }

    //var numPath = 0

    fun searchTop(i: Int): Boolean{
    	if (i == M) {
    		return true
    	}

    	for (j in aMap[goods[i]]!!){
    		if (i == 0 || j >= configuration1[i - 1]){
    			configuration1[i] = j
    			if (searchTop(i + 1)) return true else return false
    		}
    	}
    	return false
    }

    fun searchLast(i: Int): Boolean{
    	//very elegant algorithm!!!!
    	if (i == -1) return true
    	for (j in aMap[goods[i]]!!){
    		if (i == M - 1 || j <= configuration2[i + 1]){
    			configuration2[i] = j
    			if (searchLast(i - 1)) return true else return false
    		}
    	}
    	return false
    }

    if (!searchTop(0)) return println("impossible")
    else{
    	for (s in 0 until M){
	    	aMap[goods[s]]!!.reverse()
	    }	
    	searchLast(M - 1)
    	//println(configuration1.joinToString())
    	//println(configuration2.joinToString())
    	for (i in 0 until M){
    		if (configuration1[i] != configuration2[i]) return println("ambiguous")
    	}
    	return println("unique")
    }

    //println(configuration1.joinToString())
}