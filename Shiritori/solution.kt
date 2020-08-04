fun main(){
	val numLines = readLine()!!.toInt()
	val array = mutableSetOf<String>()
	var player = 2
	var currentword = readLine()!!
	var previousword = ""

	for (i in 0 until numLines - 1){
		array.add(currentword)
		previousword = currentword
		currentword = readLine()!!
		if (currentword in array || currentword[0] != previousword[previousword.length - 1]){
			return println("Player $player lost")
		}
		player = 3 - player
	}

	println("Fair Game")
}