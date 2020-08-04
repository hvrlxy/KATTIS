fun main(){
	val a = readLine()!!.toInt()
	if (a > 0) println(- readLine()!!.split(" ").map{it.toLong()}.filter{it < 0L}.sum()) else println(0)
}