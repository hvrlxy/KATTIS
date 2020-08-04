import kotlin.math.max
fun main(){
    readLine()!!
    val array = readLine()!!.split(" ").map{it.toInt()}

    var maxTemp = Int.MAX_VALUE
    var startDay = 1
    for (i in 0 until array.size - 2){
        if (max(array[i], array[i+ 2]) < maxTemp){
            startDay = i + 1
            maxTemp = max(array[i], array[i+ 2])
        }
    }

    println("$startDay $maxTemp")
}