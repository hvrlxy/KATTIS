fun main(){

	var aline = readLine()
	var aMap = mutableMapOf<String, Int>()
	var bMap = mutableMapOf<Int, String>()
	while (aline != null){
		val command = aline.split(" ")
		if (command[0] == "clear"){
			aMap = mutableMapOf<String, Int>()
			bMap = mutableMapOf<Int, String>()
		}
		else if (command[0] == "def") {
			aMap[command[1]] = command[2].toInt()
			bMap[command[2].toInt()] = command[1]
		}
		else{
			var presult = StringBuilder()
			var result  = 0
			var sign = 1
			var isValid = true
			for (i in 1 until command.size){
				if (command[i] in aMap.keys) result += sign * aMap[command[i]]!!
				else if (command[i] == "+") sign = 1
				else if (command[i] == "-") sign = -1
				else if (command[i] != "="){
					isValid = false
				}
				presult.append("${command[i]} ")
			}
			if (result !in bMap.keys || result != aMap[bMap[result]!!]!!) isValid = false
			if (isValid){
				presult.append("${bMap[result]}")
			}
			else{
				presult.append("unknown")
			}
			println(presult)
		}
		aline = readLine()
	}
}