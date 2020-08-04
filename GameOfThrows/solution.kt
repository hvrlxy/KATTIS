import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a = rd.readInts()

    var currentChild = 0
    val instructions = rd.readStrings()
    val stack = mutableListOf<Int>()
    var i = 0
    while (i < instructions.size){
    	if (instructions[i] == "undo"){
    		i ++
    		repeat(instructions[i].toInt()){
    			stack.removeAt(stack.size - 1)
    		}
    		i++
    	}
    	else{
    		stack.add(instructions[i].toInt())
    		i ++
    	}
    }

    for (i in 0 until stack.size){
    	currentChild = (currentChild + stack[i]) % a[0]
    	if (currentChild < 0) currentChild += a[0]
    }
    println(currentChild)
}