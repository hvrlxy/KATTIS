import java.io.* 

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val instruction = rd.readInts()

    fun one(a: MutableList<Int>): String{
    	val a = a.toMutableSet()
        for (i in a){
            if (7777 - i in a) return "Yes"
        }
        return "No"
    }

    fun two(a: MutableList<Int>): String{
    	var aSet = a.toMutableSet()
        if (aSet.size < a.size) return "Contains duplicate"
        return "Unique"
    }

    fun three(a: MutableList<Int>): Int{
    	var aMap = mutableMapOf<Int, Int>()
        for (i in a){
            if (i !in aMap.keys) aMap[i] = 1
            else{
                val c = aMap[i]!!
                aMap[i] = c + 1
            }
            if (aMap[i]!! > a.size / 2) return i
        }
    	return -1
    }

    fun four(a: MutableList<Int>): String{
    	if (a.size % 2 == 1){
    		a.sort()
    		return "${a[a.size/2]}"
    	}
    	else{
    		a.sort()
    		return "${a[a.size/2 - 1]} ${a[a.size/2]}"
    	}
    }

    fun five(a: MutableList<Int>){
    	val b = a.filter{ it >= 100 && it <= 999 }.sorted()
    	println(b.joinToString(separator = " "))
    }

    val a = rd.readInts().toMutableList()

    if (instruction[1] == 1) println(one(a))
    if (instruction[1] == 2) println(two(a))
    if (instruction[1] == 3) println(three(a))
    if (instruction[1] == 4) println(four(a))
    if (instruction[1] == 5) five(a)
}