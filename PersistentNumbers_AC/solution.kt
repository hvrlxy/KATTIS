import kotlin.math.*
import java.math.BigInteger

fun main(){
	var n = readLine()!!.toBigInteger()

	while (n != "-1".toBigInteger()){
		if (n < "10".toBigInteger()) println("1${n.toString()}")
		else{
			var exponent2 = 0
			while (n % "2".toBigInteger() == "0".toBigInteger()){
				n /= "2".toBigInteger()
				exponent2 ++
			}
			
			var exponent3 = 0
			while (n % "3".toBigInteger() == "0".toBigInteger()){
				n /= "3".toBigInteger()
				exponent3 ++
			}

			var exponent5 = 0
			while (n % "5".toBigInteger() == "0".toBigInteger()){
				n /= "5".toBigInteger()
				exponent5 ++
			}

			var exponent7 = 0
			while (n % "7".toBigInteger() == "0".toBigInteger()){
				n /= "7".toBigInteger()
				exponent7 ++
			}
			if(n > "1".toBigInteger()) println("There is no such number.")
			else{
				var digits = IntArray(10){0}

				digits[9] = exponent3 / 2
				exponent3 = exponent3 % 2

				digits[8] = exponent2 / 3
				exponent2 = exponent2 - digits[8] * 3

				digits[7] = exponent7
				digits[6] = min(exponent2, exponent3)

				exponent2 -= digits[6]
				exponent3 -= digits[6]

				digits[5] = exponent5
				digits[4] = exponent2 / 2
				exponent2 = exponent2 - digits[4] * 2

				digits[3] = exponent3
				digits[2] = exponent2

				println("${"2".repeat(digits[2])}${"3".repeat(digits[3])}${"4".repeat(digits[4])}${"5".repeat(digits[5])}${"6".repeat(digits[6])}${"7".repeat(digits[7])}${"8".repeat(digits[8])}${"9".repeat(digits[9])}")
			}
		}
		n = readLine()!!.toBigInteger()
	}
}