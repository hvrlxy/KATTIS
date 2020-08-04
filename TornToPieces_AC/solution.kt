import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numPieces = rd.readInt()
    var aMap = mutableMapOf<String, Int>()

    var adj = mutableListOf<MutableList<Int>>()
    var count = 0

    repeat(numPieces){
    	val array = rd.readStrings()

    	if (array[0] !in aMap.keys) {
    		aMap[array[0]] = count
    		adj.add(mutableListOf<Int>())
    		count ++
    	}
    	for (i in 1 until array.size){
    		if (array[i] !in aMap.keys) {
    			aMap[array[i]] = count
    			count ++
    			adj.add(mutableListOf<Int>())
    		}
    		if (aMap[array[i]]!! !in adj[aMap[array[0]]!!]) adj[aMap[array[0]]!!].add(aMap[array[i]]!!)
    		if (aMap[array[0]]!! !in adj[aMap[array[i]]!!]) adj[aMap[array[i]]!!].add(aMap[array[0]]!!)
    	}
    }

    var array = rd.readStrings()
    if (array[0] !in aMap.keys || array[1] !in aMap.keys)  return println("no route found")

    val startingPosition = aMap[array[0]]!!
    val endingPosition = aMap[array[1]]!!

    //println(aMap)
    //println(adj)

    val parentArray = IntArray(aMap.size){ Int.MAX_VALUE }

    val Q = mutableListOf<Int>()
    Q.add(startingPosition)
    parentArray[startingPosition] = startingPosition
    while (Q.isNotEmpty()){
    	val v = Q[0]
    	Q.removeAt(0)

    	for (w in adj[v]){
    		//println("w: $w, parentArray[w]: ${parentArray[w]}")
    		//if (w == endingPosition) break
    		if (parentArray[w] == Int.MAX_VALUE ){
    			Q.add(w)
    			parentArray[w] = v
    		}
    	}
    	//println("Q : $Q")
    }

    if (parentArray[endingPosition] == Int.MAX_VALUE) return println("no route found")
    val keyArray = aMap.keys.toMutableList()
    var routeArray = mutableListOf<String>()
    var currentSpot = endingPosition
    while (currentSpot != startingPosition){
    	routeArray.add(keyArray[currentSpot])
    	currentSpot = parentArray[currentSpot]
    }

    routeArray.add(keyArray[startingPosition])
    routeArray.reverse()
    println(routeArray.joinToString(separator = " "))
}






