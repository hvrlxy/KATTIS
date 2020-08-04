import java.io.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))
	fun gcd(x: Int, y: Int): Int{
        if (x == 0) return y
        return gcd(y.rem(x), x) 
    }

    val result = StringBuilder()

    repeat(rd.readInt()){
    	val a = rd.readInts()

    	val d = gcd(a[0], a[1])

    	if (a[2] % d == 0) result.append("Yes\n") else result.append("No\n")
    }
    print(result)
}