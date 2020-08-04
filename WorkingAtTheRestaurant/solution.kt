import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var numInstructions = rd.readInt()
	while (numInstructions != 0){
		var pileInput = 0
		var pileOuput = 0

		repeat(numInstructions){
			val instruction = rd.readStrings()

			if (instruction[0] == "DROP"){
				pileInput += instruction[1].toInt()
				println("DROP 1 ${instruction[1]}")
			}

			else if (instruction[0] == "TAKE"){
				if (pileOuput == 0){
					pileOuput += pileInput
					println("MOVE 1->2 ${pileOuput}")
					pileInput = 0
					println("TAKE 2 ${instruction[1]}")
					pileOuput -= instruction[1].toInt()
				}

				else if (pileOuput >= instruction[1].toInt()){
					println("TAKE 2 ${instruction[1]}")
					pileOuput -= instruction[1].toInt()
				}

				else{
					val alreadyTaken = pileOuput
					println("TAKE 2 ${pileOuput}")
					pileOuput = 0
					println("MOVE 1->2 ${pileInput}")
					pileOuput = pileInput
					pileInput = 0
					println("TAKE 2 ${instruction[1].toInt() - alreadyTaken}")
					pileOuput -= instruction[1].toInt() - alreadyTaken
				}
			}
		}

		numInstructions = rd.readInt()
		if (numInstructions != 0) println()
	}
}