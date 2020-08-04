import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a = rd.readInts()

    val originalGraph = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}
    val reverseGraph = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}
    val indegreeArray = IntArray(a[0]){0}

    repeat(a[2]){
    	val a1 = rd.readInts()
    	originalGraph[a1[0]].add(a1[1])
    	indegreeArray[a1[1]] ++
    	reverseGraph[a1[1]].add(a1[0])
    }

    lateinit var spiesList : MutableList<Int>

    if (a[1] > 0) spiesList = rd.readInts().toMutableList() else spiesList = mutableListOf<Int>()
    val spiesSet = spiesList.toSet()

    var confidentialCount = 0

    val dArray = BooleanArray(a[0]){true}

    fun confidentialSearch(v: Int){
    	if (dArray[v]) dArray[v] = false
    	for (w in reverseGraph[v]){
    		if (dArray[w]){
    			if (w !in spiesSet) confidentialCount ++
    			confidentialSearch(w)
    		} 
    	}
    }

    for (s in spiesList){
    	if (dArray[s]) confidentialSearch(s)
    }

    fun ComponentSearch(v: Int){
    	if (dArray[v]) dArray[v] = false
    	for (w in originalGraph[v]){
    		if (dArray[w]) ComponentSearch(w)
    	}
    }

    for (i in 0 until a[0]){
    	if (dArray[i] && indegreeArray[i] == 0) {
    		confidentialCount ++
    		ComponentSearch(i)
    	}
    }

    for (i in 0 until a[0]){
    	if (dArray[i]) {
    		confidentialCount ++
    		ComponentSearch(i)
    	}
    }

    println(confidentialCount)
}