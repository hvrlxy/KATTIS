import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

/*
    I use java queue in this implementation for speed, but feel free to use ArrayDeque 
    or mutableList
*/

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val aline = rd.readInts()
    val A = aline[0]
    val B = aline[1]
    val T = aline[2]
    val tA = aline[3]
    val tB = aline[4]

    val C = rd.readInt()

    data class Company (val a: Int, val b: Int)

    val companyList = mutableListOf<Company>()
    repeat(C){
        val bline = rd.readInts()
        if ((bline[0] - A) * tA + (bline[1] - B) * tB <= T){
            companyList.add(Company(bline[0], bline[1]))
        }
    }

    companyList.sortBy{it.a}
    val s = companyList[0].a - A
    val e = companyList[companyList.size - 1].a - A

    var result = 0

    for (a in s .. e){
        val b = (T - s * tA) / tB
        var offer = 0
        for (c in companyList){
            if (c.a <= a && c.b <= b) offer ++
            else if (c.a > a) break
        }
        if (offer > result) result = offer
    }

    println(result)
}