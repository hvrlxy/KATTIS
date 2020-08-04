fun main(){
	val aMap = mapOf("A" to 1, "B" to 2, "C" to 3, "D" to 4, "E" to 5, "F" to 6, "G" to 7, "H" to 8)
	val bMap = mapOf(1 to "A", 2 to "B", 3 to "C", 4 to "D", 5 to "E", 6 to "F", 7 to "G", 8 to "H")

	val blackDiagonal = listOf((1 to 1), (2 to 2), (3 to 3), (4 to 4), (5 to 5), (6 to 6), (7 to 7), (8 to 8))
	val whiteDiagonal = listOf((1 to 8), (2 to 7), (3 to 6), (4 to 5), (5 to 4), (6 to 3), (7 to 2), (8 to 1))

	val numCase = readLine()!!.toInt()
	val result = StringBuilder()
	repeat(numCase){
		val line = readLine()!!.split(" ")

		val start = (aMap[line[0]]!! to line[1].toInt())
		val end = (aMap[line[2]]!! to line[3].toInt())

		if ((start.first + start.second - end.first - end.second) % 2 != 0) result.append("Impossible\n")
		else{
			val moveList = mutableListOf<Pair<Int, Int>>()
			moveList.add(start)
			if ((start.first + start.second) % 2 == 0){
				for (i in blackDiagonal){
					if (start.first < i.first && start.second > i.second && i.first + i.second - start.first - start.second == 0){
						moveList.add(i)
						break
					}
					if (start.first > i.first && start.second < i.second && i.first + i.second - start.first - start.second == 0){
						moveList.add(i)
						break
					}
				}
				for (i in blackDiagonal){
					if (end.first < i.first && end.second > i.second && i.first + i.second - end.first - end.second == 0){
						if (i !in moveList) moveList.add(i)
						break
					}
					if (end.first > i.first && end.second < i.second && i.first + i.second - end.first - end.second == 0){
						if (i !in moveList) moveList.add(i)
						break
					}
				}
			}
			else{
				for (i in whiteDiagonal){

					if (start.first < i.first && start.second < i.second && i.first - start.first == i.second - start.second){
						moveList.add(i)
						break
					}
					if (start.first > i.first && start.second > i.second && i.first - start.first == i.second - start.second){
						moveList.add(i)
						break
					}
				}
				for (i in whiteDiagonal){
					if (end.first > i.first && end.second > i.second && i.first - end.first == i.second - end.second){
						if (i !in moveList) moveList.add(i)
						break
					}
					if (end.first < i.first && end.second < i.second && i.first - end.first == i.second - end.second){
						if (i !in moveList) moveList.add(i)
						break
					}
				}
			}
			if (moveList[moveList.size - 1] != end) moveList.add(end)
			result.append("${moveList.size - 1} ")
			for (i in 0 until moveList.size - 1){
				result.append("${bMap[moveList[i].first]!!} ${moveList[i].second} ")
			}
			result.append("${bMap[moveList[moveList.size - 1].first]!!} ${moveList[moveList.size - 1].second}\n")
		}
	}
	print(result)
}