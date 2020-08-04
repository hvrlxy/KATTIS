import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val s = rd.readLn()
	val t = rd.readLn()

	val aMap = mutableMapOf<Char, Int>()

	val adj = mutableListOf<MutableList<Int>>()
	var count = 0

	for (c in 0 until s.length){
		if (s[c] in t){
			if (s[c] !in aMap.keys) {
				aMap[s[c]] = count
				count ++
				adj.add(mutableListOf<Int>())
			}

			adj[aMap[s[c]]!!].add(c)
		}
	}
	for (i in t){
		if (i !in aMap.keys) return println("0")
	}

	var firstPosition = -1
	var result = 0L
	loop@for (i in adj[aMap[t[0]]!!]){
		var currentPosition = i
		var startPosition = i
		var isValid = true

		for (j in 1 until t.length){
			for (k in 0 until adj[aMap[t[j]]!!].size){
				if (adj[aMap[t[j]]!!][k] > currentPosition) {
					currentPosition = adj[aMap[t[j]]!!][k]
					break
				}

				if (k == adj[aMap[t[j]]!!].size - 1 && adj[aMap[t[j]]!!][k] < currentPosition) {
					//isValid = false
					break@loop
				}
			}
		}
		if (isValid){
			result += (startPosition - firstPosition).toLong() * (s.length - currentPosition)
		}

		firstPosition = startPosition
	}

	println(result)
}