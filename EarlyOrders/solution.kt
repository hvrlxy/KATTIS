import java.io.*
import java.util.LinkedList
import java.util.Stack

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val input = Array<String>(s[0]){rd.readLn()}

    val d = BooleanArray(s[1] + 1)
    val stack = Stack<String>()

    for (i in s[0] - 1 downTo 0){
    	if (!d[input[i].toInt()]){
    		stack.push(input[i])
    		d[input[i].toInt()] = true
    	}
    }

    val result = StringBuilder()
    val d1 = BooleanArray(s[1] + 1)
    while (stack.isNotEmpty()){
    	val n = stack.pop()
    	if (!d1[n.toInt()]){
    		result.append("$n ")
    		d1[n.toInt()] = true
    	}
    }

    println(result.deleteCharAt(result.length - 1))
}