import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readInts() = readLn().split(" ").map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val a = rd.readInts()
	val birdList = mutableListOf<Int>()

	repeat(a[2]){
		birdList.add(rd.readInt())
	}

	var result = 0
	birdList.sort()

	if ()
	for (i in 0 until birdList.size - 1){
		val dist = birdList[i + 1] - birdList[i]
		result += dist / a[1]
	}

	for (i in 6 until birdList[0] step a[1]){
		if (birdList[0] - i < a[1]) result ++
	}

	
}