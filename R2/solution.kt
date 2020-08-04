fun main(){
	val s = readLine()!!.split(" ").map{it.toInt()}
	println(s[1] * 2 - s[0])
}