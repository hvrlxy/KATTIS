import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

data class Edge(var first: Int, var second: Int, var third: Int)

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()
    val result = StringBuilder()

    repeat(numCase){
    	val a = rd.readInts()

    	var numMilks = a[0]
    	val edgesList = mutableListOf<Edge>()

    	repeat(a[1] * (a[1] - 1) / 2){
    		val a1 = rd.readInts()
    		edgesList.add(Edge(a1[0], a1[1], a1[2]))
    	}

    	edgesList.sortBy{it.third}
    	val aSet = DisjointSet(a[1])

    	var  milkNeeded = 0
    	var e = 0
    	for (v in edgesList){
    		if (aSet.find(v.first) != aSet.find(v.second)){
    			aSet.union(v.first, v.second)
    			milkNeeded += (v.third)
    			e ++
                if (e > a[1] - 1) break
    		}
    		if (numMilks - milkNeeded < a[1]) break
    	}
    	if (numMilks - milkNeeded >= a[1]) result.append("yes\n") else result.append("no\n")
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


