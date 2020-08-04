import java.util.ArrayDeque

val MOD = 2147483647

fun main(){
	val n = readLine()!!.toInt()

	val blockSet = mutableSetOf<Pair<Int, Int>>()
	val openSpot = mutableSetOf<Pair<Int, Int>>()

	for (i in 0 until n){
		val a = readLine()!!
		for (j in 0 until n){
			if (a[j] == '.') openSpot.add(i to j) else blockSet.add(i to j)
		}
	}

	fun search(i: Int, j: Int): Long{
		if (i == n - 1 && j == n - 1) return 1L
		else if ((i to j) in blockSet || (i to j) !in openSpot) return 0L
		else return search(i + 1, j) + search(i, j + 1)
	}

	val numRoads = search(0,0) % MOD\

	if (numRoads != 0L) return println(numRoads)

	val d = mutableSetOf<Pair<Int, Int>>()

	val Q = ArrayDeque<Pair<Int, Int>>()
	Q.add(0 to 0)

	while (Q.isNotEmpty()){
		val v = Q.getFirst()
		Q.removeFirst()
		if (v == (n-1 to n-1)) return println("THE GAME IS A LIE")

		if ((v.first + 1 to v.second) !in d && (v.first + 1 to v.second) in openSpot) {
			Q.add(v.first + 1 to v.second)
			d.add(v.first + 1 to v.second)
		}
		if ((v.first - 1 to v.second) !in d && (v.first - 1 to v.second) in openSpot) {
			Q.add(v.first - 1 to v.second)
			d.add(v.first - 1 to v.second)
		}
		if ((v.first to v.second + 1) !in d && (v.first to v.second + 1) in openSpot) {
			Q.add(v.first to v.second + 1)
			d.add(v.first to v.second + 1)
		}
		if ((v.first to v.second - 1) !in d && (v.first to v.second - 1) in openSpot) {
			Q.add(v.first to v.second - 1)
			d.add(v.first to v.second - 1)
		}
	}

	println("INCONCEIVABLE")
}









