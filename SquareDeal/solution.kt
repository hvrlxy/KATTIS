fun main(){
	var a = readLine()!!.split(" ")
	val Square1 = (a[0].toInt() to a[1].toInt())

	a = readLine()!!.split(" ")
	val Square2 = (a[0].toInt() to a[1].toInt())

	a = readLine()!!.split(" ")
	val Square3 = (a[0].toInt() to a[1].toInt())

	//println(Square1)
	//println(Square2)
	//println(Square3)

	if ((Square1.first == Square2.first && Square2.first == Square3.first) && 
		(Square1.second + Square2.second + Square3.second == Square1.first)) return println("YES")

	if ((Square1.second + Square2.second == Square1.first) && (Square2.second == Square3.second) &&
		(Square2.first + Square3.second == Square1.first)) return println("YES")

	if ((Square3.first == Square2.first) && (Square3.second + Square2.second == Square1.first) &&
		(Square1.second + Square2.first == Square1.first)) return println("YES")

	if ((Square2.second == Square3.first) && (Square1.second + Square2.second == Square1.first)
		&& (Square3.second + Square2.first == Square1.first)) return println("YES")

	println("NO")
}