import java.io.*
import kotlin.math.roundToInt

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readDouble() = readLn()!!.toDouble() // single Double
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readDoubles() = readStrings().map { it.toDouble() } // list of Doubles

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val aList = rd.readDoubles().toMutableList()
    aList.sort()

    val target = rd.readDouble()

    if (((aList[0] + aList[1] + aList[2]) * 100).roundToInt() > (target * 3 * 100).roundToInt()) return println("impossible")

    if (((aList[3] + aList[1] + aList[2]) * 100).roundToInt() <= (target * 100 * 3).roundToInt()) return println("infinite")

    val x = target * 3.0 - aList[1] - aList[2]
	println("%.2f".format(x))
}