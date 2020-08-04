import kotlin.math.min

fun main(){
    val s = readLine()!!
    val r = s.reversed()

    fun findFactor(s: String): Int{
    	if (kmp(s, "meow")) return 0
    	else if (kmp(s, "meo") || kmp(s, "eow")) return 1
    	else if (kmp(s, "me") || kmp(s, "eo") || kmp(s, "ow")) return 2
    	else if (kmp(s, "m") || kmp(s, "e") || kmp(s, "o") || kmp(s, "w")) return 3
    	else return 4
    }

    woem

    println(min(findFactor(s), findFactor(r)))
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
			j = lps[j - 1]
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