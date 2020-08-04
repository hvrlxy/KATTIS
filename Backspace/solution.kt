fun main(){
	val aString = readLine()!!.split("")
	val result = StringBuilder()
	for (i in 0 until aString.size){
		if (aString[i] == "<"){
			if (result.length != 0) result.deleteCharAt(result.length - 1)
		}
		else{
			result.append(aString[i])
		}
	}
	println(result)
}