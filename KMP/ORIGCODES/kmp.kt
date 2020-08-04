fun main(){
	val t = "AAAAABAAABA"
	val p = "AAAA"
	println("t : $t")
	println("p : $p")
	kmp(t,p)
}

fun kmp(t: String, p: String){
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
			println("Pattern found at ${i - j}")
			j = lps[j - 1]
		}

		else if (i < n && p[j] != t[i]){
			if (j != 0) j = lps[j - 1] else i ++
		}
	}
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
