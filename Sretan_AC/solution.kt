fun main(){

	val powerOf2Array = intArrayOf(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024)
	fun powerOf2(n: Int): Int{
		if (n <= 10) return powerOf2Array[n]
		val half = powerOf2(n/2)
		if (n % 2 == 0) return half * half else return half * half * 2
	}

	fun toBinary(decimalNumber: Int) : String {
		var decimalNumber = decimalNumber
	    if (decimalNumber == 0) return "0"
	    var result = StringBuilder()
	    while (decimalNumber > 0){
	    	result.append((decimalNumber % 2).toString())
	    	decimalNumber /= 2

	    }
	    return result.reverse().toString()
	}

	val n = readLine()!!.toInt()
	var sum = 0
	var i = 0
	while (sum < n){
		i ++
		sum += powerOf2(i)
		println("sum: $sum when i = $i")
	}

	sum -= powerOf2(i)
	println("places: $sum")

	val decimal = n - sum
	var BinaryString = toBinary(decimal)

	println("decimal: $decimal i: $i, binaryString: $BinaryString")

	val result = StringBuilder()

	result.append("4".repeat(i - 1 - BinaryString.length))
	for (c in BinaryString){
		if (c == '0') result.append("4") else result.append("7")
	}

	println(result)
}