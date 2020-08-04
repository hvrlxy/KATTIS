import kotlin.math.pow

fun main(){
	val num = readLine()!!.toInt()

	val sizeNum = findSize(num)[0]
	val position = findSize(num)[1]

	println("length of the bit string: $sizeNum in position: $position")

	if (sizeNum % 2 == 0){
		val p = convertToBinary(position)
		//println(p)
		var result = "1" + "0".repeat(sizeNum / 2 - p.length) + p
		println(p)
		result += result.reversed()
		println(result)
		println(convertToDecimal(result))
	}
	else{
		val p = convertToBinary(position)
		println(p)
		var result = "1" + "0".repeat(sizeNum / 2 - p.length) + p
		result += result.reversed().slice(1..result.length)
		println(result)
		println(convertToDecimal(result))
	}
}

fun findSize(num: Int): IntArray{
	var result = 0
	var i = 0
	while (result < num){
		for (j in 0 until i){
			result = 2 * (2 shl j)
		}
		i ++
	}
	//println("the result of the sum is $result when i = $i when the number is $num")

	if (result + (2 shl (i + 1)) < num){
		return intArrayOf(i, num - result - (2 shl (i + 1))) //first index if the size of the string, the second index if the position
	}
	//println(num - result - (2 shl (i + 1)))
	return intArrayOf(i + 1, num - result + i + 1)	
}

fun convertToBinary(x: Int): String {
	var result = ""
	var x1 = x
	if (x1 == 1){
		return ("1")
	}
	while (x1 != 1){
		if (x1 % 2 == 1){
			result += "1"
			x1 /= 2
		}
		else{
			result += "0"
			x1 /= 2
		}
		//println(result)
	}
	return result.reversed()
}

fun convertToDecimal(x: String): Int{
	var result = 0
	for (i in 0 until x.length){
		if (x.reversed()[i] == '1'){
			result += 2 shl (i - 1)
			//println("when i = $i then the result += ${2 shl i}")
		}
	}
	return result
}