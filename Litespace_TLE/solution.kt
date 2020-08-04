fun main(){
	val s = readLine()!!

	var i = 0

	val result = StringBuilder()

	val aStack = mutableListOf<Int>()
	while (i < s.length - 1){
		if (s.substring(i .. i + 1) == "SS"){
			i = i + 2
			var binary = StringBuilder()
			if (s[i] == 'S') binary.append("+") else if (s[i] == 'T') binary.append("-")
			i ++
			while (s[i] != 'N'){
				if (s[i] == 'S') binary.append("0") else binary.append("1")
				i ++
			}
			//result.append("$binary ${binary.toInt(2)}")
			aStack.add(binary.toString().toInt(2))
			i ++
		}
		else if (i < s.length - 2 && s[i] == 'S' && s[i + 1] == 'N' && s[i + 2] == 'S'){
			if (aStack.size == 0) result.append("Invalid copy operation\n") else aStack.add(aStack[aStack.size - 1])
		}
		else if (i < s.length - 2 && s[i] == 'S' && s[i + 1] == 'N' && s[i + 2] == 'T'){
			if (aStack.size < 2) result.append("Invalid swap operation\n")
			else{
				val first = aStack[aStack.size - 1]
				aStack[aStack.size - 1] = aStack[aStack.size - 2]
				aStack[aStack.size - 2] = first
			}
			i += 3
		}
		else if (i < s.length - 2 && s[i] == 'S' && s[i + 1] == 'N' && s[i + 2] == 'N'){
			if (aStack.size == 0) result.append("Invalid remove operation\n") else aStack.removeAt(aStack.size - 1)
			i += 3
		}
		else if (i < s.length - 3 && s[i] == 'T' && s[i + 1] == 'S' && s[i + 2] == 'S' && s[i + 3] == 'S'){
			if (aStack.size < 2) result.append("Invalid addition operation\n")
			else{
				val s = aStack[aStack.size - 1] + aStack[aStack.size - 2]
				aStack.removeAt(aStack.size - 1)
				aStack.removeAt(aStack.size - 1)
				aStack.add(s)
			}
			i += 4
		}
		else if (i < s.length - 3 && s[i] == 'T' && s[i + 1] == 'S' && s[i + 2] == 'S' && s[i + 3] == 'T'){
			if (aStack.size < 2) result.append("Invalid subtraction operation\n")
			else{
				val s = - aStack[aStack.size - 1] + aStack[aStack.size - 2]
				aStack.removeAt(aStack.size - 1)
				aStack.removeAt(aStack.size - 1)
				aStack.add(s)
			}
			i += 4
		}
		else if (i < s.length - 3 && s[i] == 'T' && s[i + 1] == 'S' && s[i + 2] == 'S' && s[i + 3] == 'N'){
			if (aStack.size < 2) result.append("Invalid multiplication operation\n")
			else{
				val s = aStack[aStack.size - 1] * aStack[aStack.size - 2]
				aStack.removeAt(aStack.size - 1)
				aStack.removeAt(aStack.size - 1)
				aStack.add(s)
			}
			i += 4
		}
		else if (i < s.length - 3 && s[i] == 'T' && s[i + 1] == 'S' && s[i + 2] == 'T' && s[i + 3] == 'S'){
			if (aStack.size < 2) result.append("Invalid division operation\n")
			else if (aStack[aStack.size - 1] == 0) result.append("Division by zero\n")
			else{
				val s = aStack[aStack.size - 2] / aStack[aStack.size - 1]
				aStack.removeAt(aStack.size - 1)
				aStack.removeAt(aStack.size - 1)
				aStack.add(s)
			}
			i += 4
		}
		else if (i < s.length - 3 && s[i] == 'T' && s[i + 1] == 'N' && s[i + 2] == 'S' && s[i + 3] == 'T'){
			if (aStack.size == 0) result.append("Invalid print operation\n")
			else {
				result.append("${aStack[aStack.size - 1]}\n")
				aStack.removeAt(aStack.size - 1)
			}
			i += 4
		}
	}
	print(result)
}