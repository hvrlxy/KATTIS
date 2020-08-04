import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() % 360 }.toMutableList() // list of ints

// if sequence X is a shift rotation of cycle Y, then X is a substring of YY

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numHandles = rd.readInt()

	val clock1 = rd.readInts()
	val clock2 = rd.readInts()

	clock1.sort()
	clock2.sort()

	val X = StringBuilder()
	val Y = StringBuilder()

	for (i in 0 until numHandles){
		val diffX = ((clock1[(i + 1) % numHandles] - clock1[i]) + 360) % 360
		val diffY = ((clock2[(i + 1) % numHandles] - clock2[i]) + 360) % 360

		X.append("$diffX,")
		Y.append("$diffY,")
	}

	val YY = Y.append(Y)
	if (kmp(YY.toString(), X.toString())) println("possible") else println("impossible")
    
}

fun kmp(t: String, p: String): Boolean{
	val n = t.length
	val m = p.length
	val lps = lps(p)

	var j = 0
	var i = 0
	while (i < n){
		if (p[j] == t[i]){
			i ++
			j ++
		}
		if (j == m){
			return true
			//j = lps[j - 1]
		}

		else if (i < n && p[j] != t[i]){
			if (j != 0) j = lps[j - 1] else i ++
		}
	}
	return false
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