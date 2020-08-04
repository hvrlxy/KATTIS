fun main(){
	val aString = readLine()!!
	var result = StringBuilder()
	for (char in aString){
		if (result.length == 0 || char != result.get(result.lastIndex)) result.append(char.toString())
	}
	println(result)
}