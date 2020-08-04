import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val a = rd.readInts()

	val A = rd.readInts()
	val adjMatrix = Array<IntArray>(a[0]){IntArray(a[0]){0}}
	val adjList = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}

	repeat(a[1]){
		val dependency = rd.readInts()

		adjMatrix[dependency[1]][dependency[0]] = dependency[2]
		adjList[dependency[1]].add(dependency[0])
	}

	var count = a[0] - 1
	val T = IntArray(a[0]){Int.MAX_VALUE}
    val V = IntArray(a[0]){Int.MAX_VALUE}

    fun dfs(v: Int){
        for (w in adjList[v]){
            if (T[w] == Int.MAX_VALUE) dfs(w)
        }
        T[v] = count
        V[count] = v
        count --
    }

    for (i in 0 until a[0]){
        if (T[i] == Int.MAX_VALUE) dfs(i)
    }

    val R = LongArray(a[0]){0L}

    for (i in 0 until a[0]){
    	val v = V[i]
    	R[v] += A[v].toLong()
    	for (w in adjList[v]){
    		R[w] += R[v] * adjMatrix[v][w]
    	}
    }

    println(R.joinToString(separator = " "))
}