import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val instruction = rd.readInts()

    fun one(): Int = 7

    fun two(a: IntArray): String{
    	if (a[0] > a[1]) return "Bigger"
    	else if (a[0] == a[1]) return "Equal"
    	else return "Smaller"
    } 

    fun three(a: IntArray): Int{
    	val alist = arrayOf(a[0], a[1], a[2])
    	alist.sort()
    	return alist[1]
    }

    fun four(a: IntArray): Long = a.map{it.toLong()}.sum()

    fun five(a: IntArray): Long = a.map{it.toLong()}.filter{ it%2 == 0L }.sum()

    fun six(a: IntArray): StringBuilder{
    	val alphabet = "abcdefghijklmnopqrstuvwxyz"

    	val array = a.map{ it % 26 }
    	var result = StringBuilder()
    	for (i in array) result.append(alphabet[i])
    	return result
    }

    fun seven(a: IntArray): String{
    	val d = BooleanArray(a.size){false}

    	var idx = 0
    	while(idx != a.size - 1){
    		if (idx > a.size - 1) return "Out"
    		if (d[idx]) return "Cyclic"
    		d[idx] = true
    		idx = a[idx]
    	}
    	return "Done"
    }

    val a = rd.readInts().toIntArray()

    if (instruction[1] == 1) println(one())
    if (instruction[1] == 2) println(two(a))
    if (instruction[1] == 3) println(three(a))
    if (instruction[1] == 4) println(four(a))
    if (instruction[1] == 5) println(five(a))
    if (instruction[1] == 6) println(six(a))
    if (instruction[1] == 7) println(seven(a))

}