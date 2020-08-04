fun main(){
    val n = readLine()!!.toInt()

    val Aarray = IntArray(46){0}
    val Barray = IntArray(46){0}

    Aarray[1] = 0
    Aarray[2] = 1

    for (i in 3 until Aarray.size){
        Aarray[i] = Aarray[i - 1] + Aarray[i - 2]
    }

    Barray[1] = 1
    Barray[2] = 1
    Barray[3] = 2

    for (i in 4 until Barray.size){
        Barray[i] = Barray[i - 1] + Barray[i - 2]
    }

    println("${Aarray[n]} ${Barray[n]}")
}
