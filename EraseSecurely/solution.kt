fun main(){
	val numSwitches = readLine()!!.toInt()
	val before = readLine()!!
	val after = readLine()!!
	if (numSwitches % 2 == 0 && before == after) return println("Deletion succeeded")
	if (numSwitches % 2 == 1){
		for (i in 0 until before.length){
			if (before[i] == after[i]) return println("Deletion failed")
		}
		return println("Deletion succeeded")
	}
	println("Deletion failed")
}