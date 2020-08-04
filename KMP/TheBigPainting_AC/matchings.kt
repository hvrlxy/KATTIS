import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()
    val h0 = s[0]
    val w0 = s[1]
    val h = s[2]
    val w = s[3]

    val rowList = Array<String>(h0){rd.readLn()}

    var count = 1
    val aMap = mutableMapOf<String, String>()
    val bMap = mutableMapOf<Int, String>()
    var painting = StringBuilder()

    for (s in rowList){
    	if (s !in aMap.keys){
    		painting.append(count.toString())
    		aMap[s] = count.toString()
    		bMap[count] = s
    		count ++
    	}
    	else{
    		painting.append(aMap[s]!!)
    	}
    }

    val bigPainting = Array<IntArray>(h){IntArray(w){0}}

    for (i in 0 until h){
    	val aline = rd.readLn()
    	for (j in 1 until count){
    		val matchings = kmp(aline, bMap[j]!!)
    		for (k in matchings){
    			bigPainting[i][k] = j
    		}
    	}
    }

    val columnList = Array<StringBuilder>(w){StringBuilder()}

    for (i in 0 until w){
    	for (j in 0 until h){
    		columnList[i].append(bigPainting[j][i].toString())
    	}
    }

    //println(painting)

    count = 0
    for (s in columnList){
    	val result = kmp(s.toString(), painting.toString())
    	count += result.size
    }

    println(count)
}

fun kmp(t: String, p: String): MutableList<Int>{
	val n = t.length
	val m = p.length
	val lps = lps(p)
	val result = mutableListOf<Int>()

	var j = 0
	var i = 0
	while (i < n){
		if (p[j] == t[i]){
			i ++
			j ++
		}
		if (j == m){
			result.add(i - j)
			j = lps[j - 1]
		}

		else if (i < n && p[j] != t[i]){
			if (j != 0) j = lps[j - 1] else i ++
		}
	}
	return result
}

fun lps(p: String): IntArray{
	val m = p.length
	val lps = IntArray(m){0}

	var i = 1
	var l = 0
	while (i < m){
		if (p[i] == p[l]){
			l ++
			lps[i] = l
			i ++
		}
		else{
			if (l != 0) l = lps[l - 1]
			else{
				lps[i] = l
				i ++
			}
		}
	}
	return lps
}