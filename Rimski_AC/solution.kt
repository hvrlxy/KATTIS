fun romanToNumeral(s: String): Int{
	var result = 0
	if ("XC" in s) result += 90
	else if ("LXXX" in s) result = 80
	else if ("LXX" in s) result = 70
	else if ("LX" in s) result = 60
	else if ("XL" in s) result = 40
	else if ("XXX" in s) result = 30
	else if ("XX" in s) result = 20
	else if ("L" in s) result = 50
	else if (s[0] == 'X') result = 10

	if ("VIII" in s) result += 8
	else if ("VII" in s) result += 7
	else if ("IV" in s) result += 4
	else if ("VI" in s) result += 6
	else if ("III" in s) result += 3
	else if ("II" in s) result += 2
	else if ("I" in s) result += 1
	else if ("V" in s) result += 5
	else if ("IX" in s) result += 9

	return result
}

fun numeralToRoman(n: Int): String{
	if (n == 1) return "I"
	else if (n == 2) return "II"
	else if (n == 3) return "III"
	else if (n == 4) return "IV"
	else if (n == 5) return "V"
	else if (n == 6) return "VI"
	else if (n == 7) return "VII"
	else if (n == 8) return "VIII"
	else if (n == 9) return "IX"
	else if (n == 0) return ""

	if (n / 10 == 1) return "X" + numeralToRoman(n % 10)
	else if (n / 10 == 2) return "XX" + numeralToRoman(n % 10)
	else if (n / 10 == 3) return "XXX" + numeralToRoman(n % 10)
	else if (n / 10 == 4) return "XL" + numeralToRoman(n % 10)
	else if (n / 10 == 5) return "L" + numeralToRoman(n % 10)
	else if (n / 10 == 6) return "LX" + numeralToRoman(n % 10)
	else if (n / 10 == 7) return "LXX" + numeralToRoman(n % 10)
	else if (n / 10 == 8) return "LXXX" + numeralToRoman(n % 10)
	else return "XC" + numeralToRoman(n % 10)

}

fun findSmallest(n: Int): Int{
	if (n == 11) return 9
	else if (n == 21) return 19
	else if (n == 31) return 29
	else if (n == 61) return 41 //edge case
	else if (n == 71) return 49 //edge case
	else if (n == 81) return 79

	if (n == 6) return 4
	else if (n < 10) return n

	if (n / 10 == 6) return 40 + findSmallest(n % 10)
	else return (n / 10 * 10) + findSmallest(n % 10)
}

fun solve(s: String): String{
	val n = findSmallest(romanToNumeral(s))
	//println(n)
	return numeralToRoman(n)
}


fun main(){
	val s = readLine()!!
	println(solve(s))
}