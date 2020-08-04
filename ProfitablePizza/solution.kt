import java.io.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var a = rd.readLn()
    while (a != null){
	    val numPizza = a.toInt()
	    val pList = mutableListOf<Pair<Int, Int>>()
	    
	    repeat(numPizza){
	    	val p = rd.readInts()
	    	pList.add(p[0] to p[1])
	    }

	    pList.sortBy{it.first}
	    pList.reverse()
	    pList.sortBy{it.second}
	    pList.reverse()

	    val aSet = mutableSetOf<Int>()

	    var money = 0
	    for (i in 0 until pList.size){
	    	for (j in pList[i].first - 1 downTo 0){
	    		if (j !in aSet){
	    			money += pList[i].second
	    			//println(pList[i].second)
	    			aSet.add(j)
	    			break
	    		}
	    	}
	    }

	    println(money)
	    a = rd.readLn()
	    //println(a)
	}
}