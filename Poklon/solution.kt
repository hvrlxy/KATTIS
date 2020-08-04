import java.io.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

data class Interval (val s: Int, val e: Int){
	override fun toString(): String = "$s $e"
}

fun isIn(a: Interval, b: Interval): Boolean{
	// see if b is inside a
	return (a.s <= b.s && a.e >= b.e)
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numInterval = rd.readInt()

    val intervalList = mutableListOf<Interval>()
    repeat(numInterval){
    	val aline = rd.readInts()
    	intervalList.add(Interval(aline[0], aline[1]))
    }

    //create the adj list
    val adj = Array<MutableList<Int>>(numInterval){mutableListOf<Int>()}

    for (i in 0 until numInterval){
    	for (j in 0 until numInterval){
    		if (i == j) continue
    		if (isIn(intervalList[i], intervalList[j])) adj[i].add(j)
    	}
    }

    fun printAdj(){
    	for (i in 0 until numInterval){
    		println(adj[i].joinToString())
    	}
    }
    //printAdj()

    val d = Array<MutableList<Int>>(numInterval){mutableListOf<Int>()}

    fun dfs(v: Int){
    	d[v].add(v)
    	for (u in adj[v]){
    		if (d[u].isEmpty()){
    			dfs(u)
    		}
    		if (d[u].size + 1 > d[v].size){
    			d[v] = mutableListOf<Int>()
    			d[v].addAll(d[u])
    			d[v].add(v)
    		}
    	}
    }

    for (v in 0 until numInterval){
    	if (d[v].isEmpty()) dfs(v)
    }

    //println(d.joinToString())

    var max = -1
    var ans = mutableListOf<Int>()
    for (i in 0 until numInterval){
    	if (d[i].size > max){
    		max = d[i].size
    		ans = d[i]
    	}
    }
    println(max)

    for (i in max - 1 downTo 0){
    	println(intervalList[ans[i]])
    }
}