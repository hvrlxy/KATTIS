fun main(){
	val numCase = readLine()!!.toInt()
	var caseNo = 0
	repeat(numCase){
		caseNo ++
		readLine()
		val v1 = readLine()!!.split(" ").map{it.toLong()}.toMutableList()
		val v2 = readLine()!!.split(" ").map{it.toLong()}.toMutableList()
		v1.sort()
		v2.sortDescending()
		var sum = 0L
		for (i in 0 until v1.size){
			sum += v1[i]*v2[i]
		}
		println("Case #$caseNo: $sum")
	}
}