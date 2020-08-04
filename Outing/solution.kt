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
    val n = aline[0]
    val k = aline[1]

    val a = rd.readInts()

    val aSet = DisjointSet(n)

    for (i in 0 until n){
        aSet.union(i, a[i] - 1)
    }

    val subset = mutableListOf<Int>()

    for (i in 0 until n){
        if (aSet.parentArray[i] == i){
            subset.add(aSet.sizeArray[i])
        }
    }

    val s = subset.size

    val minOpt = Array<IntArray>(s){IntArray(k + 1){0}}

    for (i in 0 until s){
    	for (j in 0 .. k){
    		if (j == subset[i]) minOpt[i][j] = j
    		else if (i == 0){
    			if (j >= subset[i]) minOpt[i][j] = subset[0]
    		}
    		else{
    			minOpt[i][j] = max(minOpt[i][j], minOpt[i - 1][j])
    			if (j >= subset[i]){
    				minOpt[i][j] = max(minOpt[i][j], minOpt[i - 1][j - subset[i]] + subset[i])
    			} 
    		}
    	}
    }

    println(minOpt[s - 1][k])
}

class DisjointSet (val size: Int){
    val rankArray = ByteArray(size)
    val parentArray = IntArray(size){it}
    val sizeArray = IntArray(size){1}

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

        if (rootV == rootW) {
            return
        } 

        if (rankArray[rootV] < rankArray[rootW]){
            sizeArray[rootW] += sizeArray[rootV]
            parentArray[rootV] = rootW
        }

        else if(rankArray[rootW] < rankArray[rootV]) {
            sizeArray[rootV] += sizeArray[rootW]
            parentArray[rootW] = rootV
        }

        else{
            rankArray[rootV] ++
            sizeArray[rootV] += sizeArray[rootW]
            parentArray[rootW] = rootV
        }
    }
}