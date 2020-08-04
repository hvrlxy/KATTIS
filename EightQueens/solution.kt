data class Queen(var x: Int, var y : Int)

fun main(){
	val rowSet = mutableSetOf<Int>()
	val columnSet = mutableSetOf<Int>()

	val SWdiagonal = mutableSetOf<Int>()
	val NEdiagonal = mutableSetOf<Int>()

	var countQueens = 0

	for (i in 0 until 8){
		val aList = readLine()!!.split("").toMutableList()
		aList.removeAt(0)
		aList.removeAt(8)

		fun isValid(q: Queen): Boolean{
			if (q.x in rowSet || q.y in columnSet || (q.x - q.y) in SWdiagonal || (q.x + q.y) in NEdiagonal) return false
			return true
		}

		for (j in 0 until 8){
			if (aList[j] == "*"){
				countQueens ++
				val queen = Queen(i,j)
				if (!isValid(queen)) return println("invalid")
				else{
					rowSet.add(queen.x)
					columnSet.add(queen.y)
					SWdiagonal.add(queen.x - queen.y)
					NEdiagonal.add(queen.x + queen.y)
				}
			}
		}
	}
	if (countQueens != 8) return println("invalid")
	println("valid")
}