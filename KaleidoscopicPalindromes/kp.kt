import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	val a = sc.nextInt()
	val b = sc.nextInt()

	val k = sc.nextInt()

	var count = 0

	for (i in a .. b){
		if (checkPalindromeNumber(i, k) == true){
			count ++
		}
	}

	println(count)
}

fun checkPalindromeNumber (x: Int, upToBase: Int): Boolean {
	for (i in 2 .. upToBase){
		if (checkPalindrome(convert(x, i)) == false){
			return false
		}
	}

	return true
}

fun convert(x: Int, base: Int): String{
	var resultArray = mutableListOf<Int>()
	var x1 = x

	while (x1 > 0){
		resultArray.add(x1 % base)
		x1 = x1 / base 
	}

	resultArray.reverse()
	var aString = StringBuilder()
	for (i in 0 until resultArray.size){
		aString.append(resultArray[i])
	}

	return aString.toString()
}

fun checkPalindrome (x: String): Boolean{
	for (i in 0 until x.length){
		if (x[i] != x[x.length - i - 1]){
			return false
		}
	}
	return true
}