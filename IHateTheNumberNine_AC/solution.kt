import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readLong() = readLn().toLong() // single Long
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readLongs() = readStrings().map { it.toLong() } // list of Longs

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val aList = listOf(1L, 9L, 81L, 729L, 6561L, 59049L, 531441L, 4782969L, 43046721L, 387420489L)

    fun ninePower(d: Long): Long{
    	if (d < 10L) return aList[d.toInt()]
    	val half = ninePower(d / 2)
    	if (d % 2 == 0L) return half * half % 1000000007L
    	else return half * half * 9L % 1000000007L
    }

    val numCase = rd.readLn().toInt()
    repeat(numCase){
        var d = rd.readLong() - 1

        var result = 8L
        println(result * ninePower(d) % 1000000007)
    }
}