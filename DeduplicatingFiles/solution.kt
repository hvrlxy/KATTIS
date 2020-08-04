import java.io.*
//import java.util.PriorityQueue

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }

fun myXOR (x: Int, y: Int): Int = (x or y) and (x.inv() or y.inv()) 

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var numFiles = rd.readInt()
	while (numFiles != 0){
		//val hashingMap = mutableMapOf<Int, Int>() // record number of unique string in a hash
		val fileMap = mutableMapOf<String, Int>() // record the number of time a file appears
		val collisionMap = mutableMapOf<Int, MutableSet<String>>()
		var numCollisions = 0
		var unique = 0
		repeat(numFiles){
			val file = rd.readLn()
			var result = 0
			for (i in 0 until file.length){
				result = myXOR(result, file[i].toInt())
			}
			//println(result)
			if (result !in collisionMap.keys){
				collisionMap[result] = mutableSetOf(file)
				fileMap[file] = 1
			}
			else{
				// you have to take into account every pair of files
				if (!collisionMap[result]!!.add(file)) {
					val c = fileMap[file]!!
					fileMap[file] = c + 1
				}
				else{
					fileMap[file] = 1
				}
				
				val keys = collisionMap[result]!!.toList()
				for (i in keys){
					if (i != file) numCollisions += fileMap[i]!!
				}
			}
		}
		val keyList = collisionMap.keys.toList()

		for (i in keyList){
			unique += (collisionMap[i]!!.size)
		}
		println("$unique $numCollisions")
		numFiles = rd.readInt()
	}
}