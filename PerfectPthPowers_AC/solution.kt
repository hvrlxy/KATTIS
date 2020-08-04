import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun pow(b: Int, e: Int): Long{
    var pow = 1L
    var e = e
    var b = b
    while (e > 0){
        if (e % 2 == 0){
            b *= b
            e /= 2
        }
        else{
            pow *= b
            e --
        }
    }
    return pow
}

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var n = rd.readInt().toLong()
	//println(n)
	while (n != 0L){
		//println(n)
		if (n > 0){
			val sq = sqrt(n.toDouble()).toInt() + 1
			for (i in sq downTo 1){
				val l = (n.toDouble().pow(1.0/i)).roundToInt()
				if (pow(l, i) == n){
					println(i)
					break
				}
			}
		}
		else{
			n = n * -1
			//println("$n ${n * -1}")
			val sq = sqrt(n.toDouble()).toInt() + 1
			//println("$n $sq")
			for (i in sq downTo 1){
				if (i % 2 == 1){
					val l = (n.toDouble().pow(1.0/i)).roundToInt()
					if (pow(l, i) == n){
						println(i)
						break
					}
				}
			}
		}
		n = rd.readInt().toLong()
	}
}