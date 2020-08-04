fun main(){
	val numLines = readLine()!!.toInt()

	val aMap = mutableMapOf<String, Int>()
	val adj = mutableListOf<MutableList<Int>>()
	var count = 0

	repeat(numLines){
		val line = readLine()!!.split(" ")
		if (line[0] !in aMap.keys){
			aMap[line[0]] = count
			count ++
			adj.add(mutableListOf<Int>())
		}
		if (line[1] !in aMap.keys){
			aMap[line[1]] = count
			count ++
			adj.add(mutableListOf<Int>())
		}

		adj[aMap[line[0]]!!].add(aMap[line[1]]!!)
	}

	var d = BooleanArray(count){ false }

	fun containCycles(v: Int, t: Int): Boolean{
        d[v] = true
        for (w in adj[v]){
            if (w == t) return true
            if (!d[w] && containCycles(w,t)) return true
        }
        return false
    }

    var d1 = BooleanArray(count){ false }

    fun dfs(v: Int): Boolean{
    	d1[v] = true
    	d = BooleanArray(count){ false }

    	if (containCycles(v,v)) return true
    	for (w in adj[v]){
    		if (!d1[w] && dfs(w)) return true
    	}
    	return false
    }

	var query = readLine()
	while(query != null){
		d1 = BooleanArray(count){ false }
		if (dfs(aMap[query]!!)) println("$query safe") else println("$query trapped")
		query = readLine()
	}
}










