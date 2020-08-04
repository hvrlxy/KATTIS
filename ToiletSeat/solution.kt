fun main(){
	val aList = readLine()!!.split("").map{it.toString()}.toMutableList()
	aList.removeAt(0)
	aList.removeAt(aList.size - 1)
	val initialPos = aList[0]
	aList.removeAt(0)
	if (initialPos == "U"){
		val allUp = aList.count{it == "D"} * 2
		val allDown = aList.size
		val asItIs = aList.count{it == "U"}
		println("$allUp $allDown $asItIs")
	}
	else{
		val allUp = aList.count{it == "U"}
		val allDown = aList.count{it == "U"} * 2
		val asItIs = aList.count{it == "D"}
		println("$allUp $allDown $asItIs")
	}

}