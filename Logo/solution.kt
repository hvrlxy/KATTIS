import kotlin.math.*
import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()
    repeat (numCase){
    	val numCommands = rd.readInt()

    	var position = (0.0 to 0.0)
    	var angle = 0.0
    	repeat(numCommands){
    		val command = rd.readStrings()

    		if (command[0] == "lt") {
    			angle = (angle + command[1].toInt())
    		}

    		if (command[0] == "rt") {
    			angle = (angle - command[1].toInt())
    		}

    		if (command[0] == "fd"){
    			val x = position.first + command[1].toInt() * cos(angle * PI/180)
    			val y = position.second + command[1].toInt() * sin(angle * PI/180)
    			position = (x to y)
    		}

    		if (command[0] == "bk"){
    			val x = position.first - command[1].toInt() * cos(angle * PI/180)
    			val y = position.second - command[1].toInt() * sin(angle * PI/180)
    			position = (x to y)
    		}
    	}

    	println(sqrt(position.first * position.first + position.second * position.second).roundToInt())
    }
}
