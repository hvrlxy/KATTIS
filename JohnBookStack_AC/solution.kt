import java.io.*

// you must use long in this problem

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun pow(k: Int): Int{
	if (k == 0) return 1
	else{
		val halves = pow(k/2)
		if (k % 2 == 0) return halves * halves else return halves * halves * 2
	}
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()

    repeat(numCase){
    	rd.readLn()

    	var array = rd.readInts().toMutableList()
    	val sortedArray = array.sorted()

    	val aMap = mutableMapOf<Int, Int>()
    	for (i in 0 until sortedArray.size){
    		if (sortedArray[i] !in aMap) aMap[sortedArray[i]] = i
    	}

    	//println(aMap)
    	
		var isSorted = false
		var result = 0L

		while (!isSorted){
			var a = -1
			var idx = -1
			for (i in 1 until array.size){
				if (array[i] < array[i - 1]){
					a = array[i]
					idx = i
					break
				}
			}
			//println(array)
			if (a == -1) isSorted = true
			else{
				result ++
				var moves = 0L
				var k = 2
				for (j in 0 until array.size){
					if (array[j] < a){
						if (j == 0) moves = 1L
						else if (array[j] == array[j - 1]) k ++
						else {
							moves *= k
							k = 2
						}
						//println(moves)
						result += moves
					}
					else{
						array.removeAt(idx)
						array.add(j, a)
						//println(array)
						break
					} 
				}
			}
		}
    	
    	println(result)
    }
}