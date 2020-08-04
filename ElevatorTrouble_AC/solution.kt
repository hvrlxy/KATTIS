fun main(){
    val aList = readLine()!!.split(" ").map {it.toInt()}

    val adj = Array<MutableList<Int>>(aList[0] + 1){mutableListOf<Int>()}

    //source = a[1]
    //sink = a[2]
    for (i in 1 .. aList[0]){
        if (i + aList[3] <= aList[0]) adj[i].add(i + aList[3])
        if (i - aList[4] > 0) adj[i].add(i - aList[4])
    }

    val d = IntArray(aList[0] + 1){Int.MIN_VALUE}
    val Q = mutableListOf<Int>()
    Q.add(aList[1])
    d[aList[1]] = 0

    while (Q.isNotEmpty()){
        val v = Q[0]
        if (v == aList[2]) return println(d[v])
        Q.removeAt(0)
        for (w in adj[v]){
            if (d[w] == Int.MIN_VALUE) {
                d[w] = d[v] + 1
                Q.add(w)
            }
        }
    }

    if (d[aList[2]] == Int.MIN_VALUE) println("use the stairs")
}