import java.io.*

private fun BufferedReader.readLn() = readLine()

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var aString = rd.readLn()
	while(aString != null){
		val domain = aString.split(" ").filter{it != "codomain"}.toMutableList()
		val bString = rd.readLn()!!
		val codomain = bString.split(" ").filter{it != "codomain"}.toMutableList()

		//println(bString.split(" ").filter{it != "codomain"}.toMutableList())

		var injective = true
		var surjective = true
		var isFunction = true

		val mapping = rd.readLn()!!.toInt()

		val aMap = mutableMapOf<String, MutableSet<String>>()
		val bMap = mutableMapOf<String, MutableSet<String>>()
		val aSet = mutableSetOf<String>()
		repeat(mapping){
			val aline = rd.readLn()!!.split(" ")

			if (aline[2] !in aMap.keys) aMap[aline[2]] = mutableSetOf(aline[0])
			else{
				if (aline[0] !in aMap[aline[2]]!!){
					injective = false
				}
			}

			if (aline[0] !in bMap.keys) bMap[aline[0]] = mutableSetOf(aline[2])
			else{
				if (aline[2] !in bMap[aline[0]]!!){
					isFunction = false
				}
			}

			aSet.add(aline[2])
		}

		if (aSet.size < codomain.size) surjective = false

		if (isFunction && injective && surjective) println("bijective")
		else if (isFunction && injective) println("injective")
		else if (isFunction && surjective) println("surjective")
		else if (!isFunction) println("not a function")
		else println("neither injective nor surjective")

		aString = rd.readLn()
	}
}