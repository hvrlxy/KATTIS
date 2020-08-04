import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var case = rd.readInts()
    var result = StringBuilder()
    while (case[0] + case[1] != 0){
        val distance = mutableListOf<Triple<Int, Int, Int>>()

        repeat(case[1]){
            val a = rd.readInts()
            distance.add(Triple(kotlin.math.min(a[0], a[1]), kotlin.math.max(a[0], a[1]) , a[2]))
        }
        val aSet = DisjointSet(case[0])
        
        distance.sortBy{it.third}
        var cost = 0L
        val edges = mutableListOf<Triple<Int, Int, Int>>()
        for (v in distance){
            if (aSet.find(v.first) != aSet.find(v.second)){
                edges.add(v)
                aSet.union(v.first, v.second)
                cost += v.third
            }
        }

        if (edges.size < case[0] - 1) result.append("Impossible\n")
        else{
            result.append("$cost\n")
            edges.sortBy{it.second}
            edges.sortBy { it.first }
            for (i in edges){
                result.append("${i.first} ${i.second}\n")
            }
        }

        case = rd.readInts()
    }
    print(result)
}

class DisjointSet (val size: Int){
    val rankArray = IntArray(size)
    val parentArray = IntArray(size){it}

    fun find (v: Int): Int{
        var v = v
        if (parentArray[v] == v) return v
        else{
            var w = find(parentArray[v])
            parentArray[v] = w
            return w
        }
    }

    fun union(v: Int, w: Int){
        var rootV = find(v)
        var rootW = find(w)

        if (rankArray[rootV] < rankArray[rootW]){
            parentArray[rootV] = rootW
        }

        else if(rankArray[rootW] < rankArray[rootV]) {
            parentArray[rootW] = rootV
        }

        else{
            rankArray[rootV] ++
            parentArray[rootW] = rootV
        }
    }
}