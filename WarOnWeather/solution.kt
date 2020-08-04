import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints
private fun BufferedReader.readDoubles() = readStrings().map { it.toDouble() } // list of ints


fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var case = rd.readInts()
	while (case[0] + case[1] != 0){
		var sateliteArray = mutableListOf<Triple<Double, Double, Double>>()
		var targetArray = mutableListOf<Triple<Double, Double, Double>>()

		repeat(case[0]){
			val a = rd.readDoubles()
			sateliteArray.add(Triple(a[0], a[1], a[2]))
		}

		repeat(case[1]){
			val a = rd.readDoubles()
			targetArray.add(Triple(a[0], a[1], a[2]))
		}

		fun dist(a: Triple<Double, Double, Double>): Double = a.first * a.first + a.second * a.second + a.third * a.third

		var result = 0
		for (i in 0 until targetArray.size){
			//println(i)
			val d1 = dist(targetArray[i]) //distance from the center of earth to the target
			//println(d1)
			for (j in 0 until sateliteArray.size){
				val d2 = dist(sateliteArray[j]) //distance from the center of the earth to the satelite
				val d3 = dist(Triple(targetArray[i].first - sateliteArray[j].first, targetArray[i].second - sateliteArray[j].second, targetArray[i].third - sateliteArray[j].third))
				if (d1 + d3 <= d2){ //check if it is not an obtuse triangle
					//println("$d1 + $d2 <= $d3")
					result ++
					break
				}
			}
		}
		println(result)
		case = rd.readInts()
	}
}