fun main(){
	val numCase = readLine()!!.toInt()

	data class Drop (val X: Double, val Y: Double, val V: Double, val color: String)
	repeat(numCase){
		val dropList = mutableListOf<Drop>()
		val numDrop = readLine()!!.toInt()
		repeat(numDrop){
			val aline = readLine()!!.split(" ")
			dropList.add(Drop(aline[0].toDouble(), aline[1].toDouble(), aline[2].toDouble(), aline[3]))
		}
		val numLocation = readLine()!!.toInt()
		repeat(numLocation){
			val aline = readLine()!!.split(" ").map{it.toDouble()}

			fun isColor(x: Double, y: Double, d: Drop): Boolean{
				val r = (x - d.X) * (x - d.X) + (y - d.Y) * (y - d.Y)
				//println("$r ${r * kotlin.math.PI} <= ${d.V}")
				if (r * kotlin.math.PI <= d.V) return true else return false
			}

			var isColored = false

			for (i in numDrop - 1 downTo 0){
				if (isColor(aline[0], aline[1], dropList[i])){
					println(dropList[i].color)
					isColored = true
					break
				}
			}
			if (!isColored) println("white")
		}
	}
}