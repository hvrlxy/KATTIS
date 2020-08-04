fun main(){
	val aline = readLine()!!.split(" ").map{it.toInt()}

	return (1 - aline[0] + 2 - aline[1] + 4 - aline[2] + 6 - aline[3] + 10 - aline[4] + 12 - aline[5] + 16 - aline[6] + 18 -aline[7])
}