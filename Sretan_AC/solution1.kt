val powerOf2Array = intArrayOf(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024)

fun powerOf2(n: Int): Int{
	if (n <= 10) return powerOf2Array[n]
	val half = powerOf2(n/2)
	if (n % 2 == 0) return half * half else return half * half * 2
}

fun toBinary(n: Int): String{
	if (n == 0) return "0"
	var result = ""
	var n = n
	while (n > 0){
		if (n % 2 == 0) result += "0" else result += "1"
		n /= 2
	}
	return result.reversed()
}

fun main(){
	val n = readLine()!!.toInt() - 1

	var sum = 0
	var i = 1
	while (sum + powerOf2(i) <= n){
		sum += powerOf2(i)
		i ++
	}
	val places = n - sum
	val binaryString = toBinary(places)

	var result = StringBuilder()
	result.append("4".repeat(i - binaryString.length))
	for (c in binaryString){
		if (c == '0') result.append("4") else result.append("7")
	}
	println(result)
}