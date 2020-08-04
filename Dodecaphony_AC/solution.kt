fun main(){
	val aMap = mapOf("C" to 0,
		"C#" to 1,
		"D" to 2,
		"D#" to 3,
		"E" to 4,
		"F" to 5,
		"F#" to 6,
		"G" to 7,
		"G#" to 8,
		"A" to 9,
		"A#" to 10,
		"B" to 11)

	val numNotes = readLine()!!.toInt()
	val aline = readLine()!!.split(" ").map{aMap[it]!!}.toIntArray()
	val bline = readLine()!!.split(" ").map{aMap[it]!!}.toIntArray()

	//println("${aline.joinToString()}\n${bline.joinToString()}")

	fun isTransposition(): Boolean{
		val n = bline[0] - aline[0]
		for (i in 0 until numNotes){
			if ((aline[i] + n + 12) % 12 != bline[i]) {
				return false
			}
		}
		return true
	}

	fun isRetrograde(): Boolean{
		for (i in 0 until numNotes){
			if (aline[i] != bline[numNotes - i - 1]) return false
		}
		return true
	}

	fun isInversion(): Boolean{
		val f = aline[0]
		for (i in 1 until numNotes){
			if ((f - (aline[i] - f) + 12) % 12 != bline[i]) return false
		}
		return true
	}

	if (isTransposition()) return println("Transposition")
	if (isRetrograde()) return println("Retrograde")
	if (isInversion()) return println("Inversion")
	println("Nonsense")
}