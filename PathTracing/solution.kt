import java.io.*

private fun BufferedReader.readLn() = readLine() // string line

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var locationsList = mutableListOf<Pair<Int, Int>>()

	var currentPosition = 0 to 0
	locationsList.add(currentPosition)

	var MinW = 0
	var MinH = 0
	var MaxW = 0
	var MaxH = 0

	var instruction = rd.readLn()
	while (instruction != null){
		if (instruction == "up"){
			currentPosition = currentPosition.first - 1 to currentPosition.second
		}
		else if (instruction == "down"){
			currentPosition = currentPosition.first + 1 to currentPosition.second
		}
		else if (instruction == "left"){
			currentPosition = currentPosition.first to currentPosition.second - 1
		}
		else{
			currentPosition = currentPosition.first to currentPosition.second + 1
		}

		locationsList.add(currentPosition)

		if (currentPosition.first > MaxH) MaxH = currentPosition.first
		if (currentPosition.first < MinH) MinH = currentPosition.first
		if (currentPosition.second > MaxW) MaxW = currentPosition.second
		if (currentPosition.second < MinW) MinW = currentPosition.second 

		instruction = rd.readLn()
	}

	MaxW -= MinW
	MaxH -= MinH

	//println("$MinW $MinH $MaxW $MaxH")

	val startPosition = (locationsList[0].first to locationsList[0].second)
	val endingPosition = (locationsList[locationsList.size - 1].first to locationsList[locationsList.size - 1].second)

	// println(locationsList)
	// println(startPosition)
	// println(endingPosition)

	val result = StringBuilder()
	result.append("#".repeat(MaxW + 3))
	result.append("\n")
	for (i in 0 .. MaxH){
		result.append("#")
		for (j in 0 .. MaxW){
			if ((i + MinH to j + MinW) == startPosition) result.append("S")
			else if ((i + MinH to j + MinW) == endingPosition) result.append("E")
			else if ((i + MinH to j + MinW) in locationsList) result.append("*") 
			else result.append(" ")
		}
		result.append("#\n")
	}
	result.append("#".repeat(MaxW + 3))
	result.append("\n")
	print(result)
}










