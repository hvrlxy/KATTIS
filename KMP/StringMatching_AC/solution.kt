import java.io.*

private fun BufferedReader.readLn() = readLine()

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var p = rd.readLn()
    while (p != null){
    	val t = rd.readLn()!!
    	println(kmp(t,p).joinToString(separator = " "))
    	p = rd.readLn()
    }
}

fun kmp(t: String, p: String): MutableList<Int>{
	var result = mutableListOf<Int>()
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