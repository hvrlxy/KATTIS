fun main(){
	val aline = readLine()!!.split(" ")

	val a = aline[0]
	val b = aline[1]

	val aMap = mapOf('0' to 6,
		'1' to 2,
		'2' to 5,
		'3' to 5,
		'4' to 4,
		'5' to 5,
		'6' to 6,
		'7' to 3,
		'8' to 7,
		'9' to 6)

	var matchA = 0
	var matchB = 0

	for (i in 0 until a.length){
		matchA += aMap[a[i]]!!
		matchB += aMap[b[i]]!!
	}

	if (matchA != matchB) return println("no")

	val selfMap = mapOf('0' to setOf('6', '9'),
		'1' to setOf(),
		'2' to setOf('3'),
		'3' to setOf('2', '5'),
		'4' to setOf(),
		'5' to setOf('3'),
		'6' to setOf('9', '0'),
		'7' to setOf(),
		'8' to setOf(),
		'9' to setOf('0','6'))

	val removeOne = mapOf('0' to setOf(),
		'1' to setOf(),
		'2' to setOf(),
		'3' to setOf(),
		'4' to setOf(),
		'5' to setOf(),
		'6' to setOf('5'),
		'7' to setOf('1'),
		'8' to setOf('0','6','9'),
		'9' to setOf('3','5'))

	val addOne = mapOf('0' to setOf('8'),
		'1' to setOf('7'),
		'2' to setOf(),
		'3' to setOf('9'),
		'4' to setOf(),
		'5' to setOf('9','6'),
		'6' to setOf('8'),
		'7' to setOf(),
		'8' to setOf(),
		'9' to setOf('8'))

	fun isSelf(): Boolean{
		var isCritical = false
		for (i in 0 until a.length){
			if (a[i] != b[i] && isCritical) return false
			if (a[i] != b[i] && !isCritical){
				if (b[i] in selfMap[a[i]]!!) isCritical = true else return false
			}
		}
		return true
	}

	if (isSelf()) return println("yes")

	fun pmOne(): Boolean{
		var match = 0
		var isCritical = false
		for (i in 0 until a.length){
			if (a[i] != b[i]){
				if (isCritical) return false
				else if (b[i] in addOne[a[i]]!! && match == 0) match ++
				else if (b[i] in addOne[a[i]]!! && match == -1){
					match ++ 
					isCritical = true
				}
				else if (b[i] in addOne[a[i]]!! && match == 1) return false
				else if (b[i] in removeOne[a[i]]!! && match == 0) match --
				else if (b[i] in removeOne[a[i]]!! && match == 1) {
					match --
					isCritical = true
				}
				else if (b[i] in removeOne[a[i]]!! && match == -1) return false
				else return false
			}
		}
		if (match == 0) return true else return false
	}

	if (pmOne()) return println("yes") else return println("no")
}










