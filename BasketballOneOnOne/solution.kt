fun main(){
	val array = readLine()!!.split("").toMutableList()

	var scoreA = 0
	var scoreB = 0

	var i = 0

	while (i < array.size){
		if (array[i] == "A"){
			i ++
			scoreA += array[i].toInt()
		}
		else if (array[i] == "B"){
			i ++
			scoreB += array[i].toInt()
		}
		i++
	}

	if (scoreA > scoreB) println("A") else println("B")
}