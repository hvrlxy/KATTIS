fun main(){
	val n = readLine()!!
	val m = readLine()!!

	if (n.length >= m.length){
		var result = StringBuilder()
		result.append(n.substring(0, n.length - m.length + 1))
		result.append(".")
		result.append(n.substring(n.length - m.length + 1, n.length))
		
		var i = result.length - 1
		while (result.get(i) == '0' || result.get(i) == '.'){
			if (result.get(i) == '.'){
				result.deleteCharAt(i)
				break
			}
			result.deleteCharAt(i)
			i --
		}
		println(result)
	}

	else{
		var result = StringBuilder()
		result.append("0.")
		result.append("0".repeat(m.length - n.length - 1))
		result.append(n)

		var i = result.length - 1
		while (result.get(i) == '0' || result.get(i) == '.'){
			if (result.get(i) == '.'){
				result.deleteCharAt(i)
				break
			}
			result.deleteCharAt(i)
			i --
		}
		println(result)
	}
}