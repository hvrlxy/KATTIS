fun main(){
	val aString = readLine()!!

	val a = aString.split("/").map{it.toInt()}.toList()

	val aList = mutableListOf<MutableList<Int>>(mutableListOf(a[0], a[1], a[2]), mutableListOf(a[0],a[2],a[1]),
		mutableListOf(a[1],a[0],a[2]), mutableListOf(a[1],a[2],a[0]), mutableListOf(a[2], a[0], a[1]), mutableListOf(a[2], a[1], a[0]))

	val validDate = mutableListOf<MutableList<Int>>()

	for (i in aList){
		if (i[0] < 1000) i[0] += 2000
	}

	fun isLeap(y: Int): Boolean{
		if (y % 400 == 0) return true
		else if (y % 100 == 0) return false
		else if (y % 4 == 0) return true
		else return false
	}

	fun isValid (d: MutableList<Int>): Boolean{
		if (d[2] == 0 || d[1] == 0) return false
		if (d[0] > 2999 || d[0] < 2000) return false
		if (d[1] > 12) return false
		if (isLeap(d[0])){
			if (d[1] == 2 && d[2] > 29) return false
			if (d[1] in listOf(1,3,5,7,8,10,12) && d[2] > 31) return false
			if (d[1] in listOf(4,6,9,11) && d[2] > 30) return false
		}
		else{
			if (d[1] == 2 && d[2] > 28) return false
			if (d[1] in listOf(1,3,5,7,8,10,12) && d[2] > 31) return false
			if (d[1] in listOf(4,6,9,11) && d[2] > 30) return false
		}
		return true
	}
	for (i in aList){
		if (isValid(i)) validDate.add(i)
	}

	if (validDate.isEmpty()) return println("$aString is illegal")

	validDate.sortBy{it[2]}
	validDate.sortBy{it[1]}
	validDate.sortBy{it[0]}

	println("${validDate[0][0]}-${"0".repeat(2 - validDate[0][1].toString().length)}${validDate[0][1]}-${"0".repeat(2 - validDate[0][2].toString().length)}${validDate[0][2]}")
}