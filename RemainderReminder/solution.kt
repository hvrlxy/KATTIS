import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

val INF = 100000000

fun inv(a: Int, m: Int): Int{
    /* return the modulos inverse of a with respect to mod m arithmetic */

    var m0 = m
    var x0 = 0
    var x1 = 1
    var a = a

    if (m == 1) return 0

    // apply extended euclid
    while (a > 1){
        val q = a % medBox

        val t = m
        m = a % m

        a = t
        t = x0
        x0 = x1 - q * x0
        x1 = t
    }

    if (x1 < 0) x1 += m0
    return x1
}

fun chineseRemainder(num: IntArray, rem: IntArray, k: Int): Int{
    /*
        Return the smallest number x such that:
            x % num[0] = rem[0]
            x % num[1] = rem[1]
            ...
            x % num[k - 1] = rem[k - 1]
        k is the size of num and rem
    */

    val prod = 1
    //calculate the product of all nums
    for (i in 0 until k){
        prod *= num[i]
    }

    var result = 0
    for (i in 0 until k){
        val pp = prod[i] / num[i]
        result += rem[i] * inv(pp, num[i]) * pp
    }
    return result
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val a = s[0] // smaller side
    val b = s[1] // bigger side

    val c = s[2] // big box
    val d = s[3] //medium box
    val e = s[4] //small box

    val f = s[5] // lower bound
    val g = s[6] // upper bound

    var limit = a / 2

    for (i in 1 .. limit){
    	for (j in i .. limit){
    		for (k in j .. limit){
    			val box1 = i * (a - 2 * i) * (b - 2 * i)
    			val box2 = j * (a - 2 * j) * (b - 2 * j)
    			val box3 = k * (a - 2 * k) * (b - 2 * k)

    			val bigBox = max(max(box1, box2), box3)
    			val smallBox = min(min(box1, box2), box3)
    			val medBox = box2 + box1 + box3 - smallBox - bigBox

    			val x = chineseRemainder(intArrayOf(bigBox, smallBox, medBox), intArrayOf(c,d,e), 3)

                
    		}
    	}
    }
}