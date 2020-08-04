import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var dimensions = rd.readInts()

    while (dimensions.sum() != 0){
    	val positionList = mutableListOf<Triple<Int, Int, Int>>()
    	val aMap = mutableMapOf<Triple<Int, Int, Int>, Int>()

    	var Q = mutableListOf<Int>()

    	var endingPosition = 0
    	var count = 0
    	for (i in 0 until dimensions[0]){
    		for (j in 0 until dimensions[1]){
    			val aString = rd.readLn()
    			for (c in 0 until dimensions[2]){
    				if (aString[c] == '.') {
    					positionList.add(Triple(i,j,c))
    					aMap[Triple(i,j,c)] = count
    					count ++
    				}
    				if (aString[c] == 'S') {
    					positionList.add(Triple(i,j,c))
    					aMap[Triple(i,j,c)] = count
    					Q.add(count)
    					count ++
    				}
    				if (aString[c] == 'E') {
    					positionList.add(Triple(i,j,c))
    					aMap[Triple(i,j,c)] = count
    					endingPosition = count
    					count ++
    				}
    			}
    		}
    		rd.readLn()
    	}

    	val keyList = aMap.keys.toMutableList()

    	val d = IntArray(positionList.size){Int.MAX_VALUE}
    	d[Q[0]] = 0

    	while (Q.isNotEmpty()){
    		val v = keyList[Q[0]]
    		Q.removeAt(0)

    		if (Triple(v.first + 1, v.second, v.third) in aMap.keys && d[aMap[Triple(v.first + 1, v.second, v.third)]!!] == Int.MAX_VALUE){
    			d[aMap[Triple(v.first + 1, v.second, v.third)]!!] = d[aMap[v]!!] + 1
    			Q.add(aMap[Triple(v.first + 1, v.second, v.third)]!!)
    		}

    		if (Triple(v.first - 1, v.second, v.third) in aMap.keys && d[aMap[Triple(v.first - 1, v.second, v.third)]!!] == Int.MAX_VALUE){
    			d[aMap[Triple(v.first - 1, v.second, v.third)]!!] = d[aMap[v]!!] + 1
    			Q.add(aMap[Triple(v.first - 1, v.second, v.third)]!!)
    		}

    		if (Triple(v.first, v.second + 1, v.third) in aMap.keys && d[aMap[Triple(v.first, v.second + 1, v.third)]!!] == Int.MAX_VALUE){
    			d[aMap[Triple(v.first, v.second + 1, v.third)]!!] = d[aMap[v]!!] + 1
    			Q.add(aMap[Triple(v.first, v.second + 1, v.third)]!!)
    		}

    		if (Triple(v.first, v.second - 1, v.third) in aMap.keys && d[aMap[Triple(v.first, v.second - 1, v.third)]!!] == Int.MAX_VALUE){
    			d[aMap[Triple(v.first, v.second - 1, v.third)]!!] = d[aMap[v]!!] + 1
    			Q.add(aMap[Triple(v.first, v.second - 1, v.third)]!!)
    		}

    		if (Triple(v.first, v.second, v.third + 1) in aMap.keys && d[aMap[Triple(v.first, v.second, v.third + 1)]!!] == Int.MAX_VALUE){
    			d[aMap[Triple(v.first, v.second, v.third + 1)]!!] = d[aMap[v]!!] + 1
    			Q.add(aMap[Triple(v.first, v.second, v.third + 1)]!!)
    		}

    		if (Triple(v.first, v.second, v.third - 1) in aMap.keys && d[aMap[Triple(v.first, v.second, v.third - 1)]!!] == Int.MAX_VALUE){
    			d[aMap[Triple(v.first, v.second, v.third - 1)]!!] = d[aMap[v]!!] + 1
    			Q.add(aMap[Triple(v.first, v.second, v.third - 1)]!!)
    		}
    	}

    	if (d[endingPosition] == Int.MAX_VALUE) println("Trapped!") else println("Escaped in ${d[endingPosition]} minute(s).")

    	dimensions = rd.readInts()
    }
}