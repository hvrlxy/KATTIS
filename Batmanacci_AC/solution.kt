import java.math.BigInteger

fun main(){
	val stat = readLine()!!.split(" ").map{ it.toBigInteger() }

	val FibArray = Array<BigInteger>(stat[0].toInt() + 1){"0".toBigInteger()}
	FibArray[1] = "1".toBigInteger()
	FibArray[2] = "1".toBigInteger()
	for (i in 3 .. stat[0].toInt()){
		FibArray[i] = FibArray[i - 1] + FibArray[i - 2]
	}

	//println(FibArray.joinToString())
	var i = stat[0].toInt()
	var j = stat[1]

	while (true){
		if (i == 1 && j == "1".toBigInteger()) return println( "N")
		else if (i == 2 && j == "1".toBigInteger()) return println("A")

		else if (j <= FibArray[i - 2]) i -= 2
		else if (j > FibArray[i - 2]){
			//println("fib ${i - 2}: ${FibArray[i - 2]}, j = $j")
			j = j - FibArray[i - 2]
			i --
		}
		//println("$i $j")
	}
}