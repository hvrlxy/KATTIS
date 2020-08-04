import java.math.BigInteger

fun main(){
	val numCase = readLine()!!.toInt()
	repeat(numCase){
		val a = readLine()!!.split(" ")
		var octal = BigInteger("0", 8)
		if ('8' !in a[1] && '9' !in a[1]) octal = BigInteger(a[1],8)
		println("${a[0]} $octal ${BigInteger(a[1],10)} ${BigInteger(a[1],16)}")
	}
}