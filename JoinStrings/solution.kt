import java.io.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numStrings = rd.readInt()
    var stringArray = Array<LinkedList>(numStrings){LinkedList("")}
    for (i in 0 until numStrings){
        stringArray[i] = LinkedList(rd.readLn())
    }

    var a = rd.readLn()
    while (a != null){
    	val instruction = a.split(" ").map{it.toInt()}
    	stringArray[instruction[0] - 1].concatenate(stringArray[instruction[1] - 1])
    	a = rd.readLn()
        if (a == null) stringArray[instruction[0] - 1].print()
    }
}

class LinkedList(s: String){
    var head : Node? = null
    var tail : Node? = null

    class Node(var c: Char){
        var head : Node? = null
        var tail: Node? = null
    }

    init{
        var currentNode = head
        for (c in s){

            val n = Node(c)
            currentNode = n
            tail = currentNode
            currentNode = currentNode.tail
        }
    }

    fun concatenate(other: LinkedList){
        if (other.head != null) tail!!.tail = other.head
    }

    fun print(){
        println()
        var currentNode = head
        while (currentNode != null){
            print(currentNode.c)
            currentNode = currentNode.tail
        }
        println()
    }
}