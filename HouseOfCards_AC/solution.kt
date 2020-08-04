import java.util.Scanner
import java.io.*
import java.math.BigInteger

fun main(args:Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))
	val h0 = sc.next()

	val h = BigInteger(h0)

	val r = h.rem(8.toBigInteger()).toInt()

	var result: BigInteger

	if ((r == 0) || (r == 5)){
		result = h
	}

	else if ((r == 1) || (r == 2) || (r == 3) || (r == 4)){
		result = h.plus((5 - r).toBigInteger())
	}

	else{
		result = h.plus((8 - r).toBigInteger())
	}

	println(result)
}