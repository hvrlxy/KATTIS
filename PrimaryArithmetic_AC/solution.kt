fun main(){
	var a = readLine()!!.split(" ").map{ it.toInt() }

	var num1 = a[0]
	var num2 = a[1]

	while (num2 + num1 != 0){
		var result = 0
		var carry = 0
		while (num1 > 0 && num2 > 0){
			val r1 = num1 % 10
			val r2 = num2 % 10

			if (r1 + r2 + carry >= 10) {
				result ++
				num2 /= 10
				num1 /= 10
				carry = (r1 + r2 + carry) / 10
			}
			else{
				num2 /= 10
				num1 /= 10
				carry = 0
			}
		}

		if (result == 0) println("No carry operation.")
		else if (result== 1) println("1 carry operation.")
		else println("$result carry operations.")

		a = readLine()!!.split(" ").map{ it.toInt() }
		num1 = a[0]
		num2 = a[1]
	}
}