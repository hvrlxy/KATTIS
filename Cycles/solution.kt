val MOD = 9901

fun main(){
	val numCase = readLine()!!.toInt()

	var caseNo = 0

	repeat(numCase){
		caseNo ++
		val s = readLine()!!.split(" ").map{it.toInt()}

		val adj = Array<MutableSet<Int>>(s[0] + 1){mutableSetOf<Int>()}

		repeat(s[1]){
			val aline = readLine()!!.split(" ").map{it.toInt()}

			adj[aline[0]].add(aline[1])
			adj[aline[1]].add(aline[0])
		}

		val pArray = IntArray(s[0] + 1){0}

		pArray[0] = 1
		pArray[s[0]] = 1

		val aSet = mutableSetOf<Int>()

		for (i in 2 .. s[0]){
			aSet.add(i)
		}

		var count = 0

		fun search(i: Int){
			if (i == s[0]) {
				//println(pArray.joinToString())
				count ++
				return
			}
			else if (aSet.isEmpty()) return

			for (j in 2 .. s[0]){
				if (j in aSet && j !in adj[pArray[i - 1]] && j !in adj[pArray[i + 1]]) {
					pArray[i] = j
					aSet.remove(j)
					search(i + 1)
					pArray[i] = 0
					aSet.add(j)
				}
			}
		}

		search(1)

		count /= 2
		println("Case #$caseNo: ${count % MOD}")
	}
}