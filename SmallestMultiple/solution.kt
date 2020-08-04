import java.math.BigInteger
import java.io.*

private fun BufferedReader.readLn() = readLine() // string line

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var a = rd.readLn()

	fun gcd(x: BigInteger, y: BigInteger): BigInteger{
		if (x == "0".toBigInteger()) return y
		return gcd(y.rem(x), x) 
	}

	fun lcm (x: BigInteger, y:BigInteger): BigInteger{
		return (x.times(y) / gcd(x, y))
	}
	while (a != null){
		val array = a.split(" ").map{ it.toBigInteger() }
		var lcm = "1".toBigInteger()
		for (i in 0 until array.size){
			lcm = lcm(lcm, array[i])
		}
		println(lcm)
		a = rd.readLn()
	}
}