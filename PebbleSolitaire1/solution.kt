import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()
	repeat(numCase){
		val line = rd.readLn()

		val alist = mutableListOf<Int>()

		for (c in 0 until 23){
			if (line[c] == 'o') alist.add(c)
		}
		val aSet = mutableSetOf<MutableList<Int>>()

		val Q = mutableListOf<MutableList<Int>>()
		Q.add(alist)
		var minimumLeft = Int.MAX_VALUE

		while (Q.isNotEmpty()){
			val a = Q[Q.size - 1]
			Q.removeAt(Q.size - 1)
			if (a.size < minimumLeft) minimumLeft = a.size

			for (n in a){
				if (n+1 in a && n+2 !in a && (n + 2) < 23){
					var b = mutableListOf<Int>()
					for (c in a){
						if (c != n && c != n + 1) b.add(c)
					}
					b.add(n + 2)
					if (b !in aSet){
						Q.add(b)
						aSet.add(b)
					}
				}
				if (n - 1 in a && n - 2 !in a && (n - 2) >= 0){
					var b = mutableListOf<Int>()
					for (c in a){
						if (c != n && c != n - 1) b.add(c)
					}
					b.add(n - 2)
					if (b !in aSet){
						Q.add(b)
						aSet.add(b)
					}
				}
			}
		}
		println(minimumLeft)
	}
}