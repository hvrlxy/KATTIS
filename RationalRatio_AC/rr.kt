import java.util.Scanner
import kotlin.math.pow

fun main(){
	val sc = Scanner(System.`in`)

	val num = sc.next()
	val repetition = sc.nextInt()

	val idx = num.indexOf(".")

	val a = (num.slice(0 .. idx - 1) + num.slice(idx + 1 .. num.length - repetition - 1)).toLong()
	val b = (num.slice(0 .. idx - 1) + num.slice(idx + 1 .. num.length - 1)).toLong()

	val c = (num.length - idx - 1).toLong()
	val d = (num.length - repetition - idx - 1).toLong()

	val TEN: Double = 10.0

	var numerator = b - a
	var denominator = (TEN.pow(c.toInt()) - TEN.pow(d.toInt())).toLong()

	val gcd = gcd(numerator, denominator)

	numerator /= gcd
	denominator /= gcd

	println("$numerator/$denominator")

	// println(a)
	// println(b)
	// println(c)
	// println(d)
}

fun gcd(x: Long, y: Long): Long{
	var newX = x
	var newY = y

	while (newY > 0){
		val z = newX % newY
		newX = newY
		newY = z
	}

	return newX
}