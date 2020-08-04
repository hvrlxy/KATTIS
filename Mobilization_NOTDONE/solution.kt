import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a = rd.readInts()

    data class Troop (var cost: Double, var efficacy: Double)

    val troopList = mutableListOf<Troop>()
    repeat(a[0]){
    	val aList = rd.readStrings().map{it.toDouble()}

    	troopList.add(Troop(aList[0], aList[1] * aList[2]))
    }

    troopList.sortBy{it.cost}
    troopList.reverse()
    troopList.sortBy{it.efficacy}

    val purchaseTroop = troopList[a[0] - 1]

    println((a[1] / purchaseTroop.cost) * purchaseTroop.efficacy)
}