fun main(){
	var caseNo = 0
	var a = readLine()
	while (a != null){
		caseNo ++
		val array = a.split(" ").map{it.toInt()}.toMutableList()
		array.removeAt(0)
		println("Case $caseNo: ${array.min()!!} ${array.max()!!} ${array.max()!! - array.min()!!}")
		a = readLine()
	}
}