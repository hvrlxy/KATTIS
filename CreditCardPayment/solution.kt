import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toDouble() }.toList() // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()
	repeat(numCase){
		val stats = rd.readInts()

		var result = 0
		var R = stats[0] // interest rate
		var B = stats[1] // balance
		var M = stats[2] // monthly payment

		//if (B * (R / 100) >= M) result = 1201

		while (result <= 1200){
			result ++
			//println("after interest: ${(1.0 + R / 100) * B}, M: $M and after payment: ${(1.0 + R / 100) * B - M}")
			B *= (1.0 + R / 100)
			B = (B * 100 + 0.5 + 1e-8).toInt() / 100.0
			B -= M
			if (B <= 0.0) break
		}

		if (result > 1200) println("impossible") else println(result)
	}
}