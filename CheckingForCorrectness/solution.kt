import java.util.Scanner
import java.math.BigInteger

val MOD = "10000".toBigInteger()

fun main(){
	val sc = Scanner(System.`in`)

	fun exponent (a: BigInteger, b: BigInteger): Int{
		if (b == "0".toBigInteger()) return 1
		val half = exponent(a, b / "2".toBigInteger())
		if (b.rem("2".toBigInteger()) == "0".toBigInteger()) return (half * half) % 10000 else return ((half * half) % 10000 * a.toInt()).toInt() % 10000
	}

	while (sc.hasNext()){
		val a = sc.next()
		val op = sc.next()
		val b = sc.next()

		if (op == "+") println("${(a.toBigInteger() % MOD  + b.toBigInteger() % MOD) % MOD}")
		else if (op == "*") println("${(a.toBigInteger() % MOD * b.toBigInteger() % MOD) % MOD}")
		else println(exponent(a.toBigInteger() % MOD, b.toBigInteger()))
	}
}