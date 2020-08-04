fun main(){
	val numCase = readLine()!!.toInt()
	repeat(numCase){
		var result = 0
		val k = readLine()!!.toInt()
		repeat(k){
			result = ((result + 0.5) * 2).toInt()
		}
		println(result)
	}
}