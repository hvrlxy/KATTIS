fun main(){
	var aline = readLine()
	while (aline != null){
		val aString = aline.toLowerCase()
		if ("problem" in aString) println("yes") else println("no")
		aline = readLine()
	}
}