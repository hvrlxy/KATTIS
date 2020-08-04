fun main(){
	val n = readLine()!!.toInt()

	val a = readLine()!!.split(" ").map{it.toInt()}

	val g = mutableListOf<Int>()
	g.add(a[0])

	for (i in 1 until n){
		if (a[i] > g[g.size - 1]) g.add(a[i])
	}
	println(g.size)
	println(g.toIntArray().joinToString(separator = " "))
}