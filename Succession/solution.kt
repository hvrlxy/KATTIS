import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val aMap = mutableMapOf<String, Int>()
    val adj = mutableListOf<MutableList<Int>>()
    val a = rd.readInts()
    aMap[rd.readLn()] = 0 // the name of the king
    adj.add(mutableListOf<Int>())
    var count = 1

    val indegreeArray = mutableListOf<Int>()
    indegreeArray.add(0)

    repeat(a[0]){
        val relation = rd.readStrings()

        if (relation[0] !in aMap.keys){
            aMap[relation[0]] = count
            adj.add(mutableListOf<Int>())
            indegreeArray.add(0)
            count ++
        }
        if (relation[1] !in aMap.keys){
            aMap[relation[1]] = count
            adj.add(mutableListOf<Int>())
            indegreeArray.add(0)
            count ++
        }
        if (relation[2] !in aMap.keys){
            aMap[relation[2]] = count
            adj.add(mutableListOf<Int>())
            indegreeArray.add(0)
            count ++
        }

        adj[aMap[relation[1]]!!].add(aMap[relation[0]]!!)
        adj[aMap[relation[2]]!!].add(aMap[relation[0]]!!)
        indegreeArray[aMap[relation[0]]!!] += 2
    }

    val Q = mutableListOf<Int>()
    val T = mutableListOf<Int>()
    for (i in 0 until indegreeArray.size){
    	if (indegreeArray[i] == 0) Q.add(i)
    }

    while (Q.isNotEmpty()){
    	val v = Q[0]
    	T.add(v)
    	Q.removeAt(0)
    	for (w in adj[v]){
    		indegreeArray[w] --
    		if (indegreeArray[w] == 0) Q.add(w)
    	}
    }

    //println("T: $T")

    val bloodArray = DoubleArray(count){0.0}
    bloodArray[0] = 100000.0

    while (T.isNotEmpty()){
    	val v = T[0]
    	T.removeAt(0)
    	for (w in adj[v]){
    		bloodArray[w] += bloodArray[v] / 2
    		//println("${w} gets ${bloodArray[v] / 2} from ${v}")
    	}
    }

    var max = Double.MIN_VALUE
    var result = ""
    //println(aMap)

    //println(bloodArray.joinToString())

    repeat(a[1]){
        val claimer = rd.readLn()
        if (claimer in aMap.keys){
        	//println(bloodArray[aMap[claimer]!!])
            if (bloodArray[aMap[claimer]!!] > max){
                max = bloodArray[aMap[claimer]!!]
                result = claimer
            }
        }
    }

    println(result)
}





