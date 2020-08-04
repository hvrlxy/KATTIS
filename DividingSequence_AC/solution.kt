fun main(){
    val n = readLine()!!.toInt()

    println(kotlin.math.log2(n.toDouble()).toInt() + 1)
    var sequence = StringBuilder()

    var i = 1
    while (i <= n){
        sequence.append(i.toString())
        sequence.append(" ")
        i *= 2
    }

    println(sequence.deleteCharAt(sequence.length - 1))
}
