lateinit var rooks : Pair<Int, Int>
lateinit var OpponentKing : Pair<Int, Int>
lateinit var King : Pair<Int, Int>

fun main(){

	for (i in 0 until 8){
		var aString = readLine()!!
		for (j in 0 until 8){
			if (aString[j] == 'K') King = (i to j)
			else if (aString[j] == 'k') OpponentKing = (i to j)
			else if (aString[j] == 'R') rooks = (i to j)
		}
	}

	if (OpponentKing.first == 0 && King.first == 2 && OpponentKing.second == King.second && 
		rooks.second != King.second && rooks.second != King.second + 1 && rooks.second != King.second - 1){
		return println("Yes")
	}

	if (OpponentKing.first == 7 && King.first == 5 && OpponentKing.second == King.second && 
		rooks.second != King.second && rooks.second != King.second + 1 && rooks.second != King.second - 1){
		return println("Yes")
	}

	if (OpponentKing.second == 0 && King.second == 2 && OpponentKing.first == King.first && 
		rooks.first != King.first && rooks.first != King.first + 1 && rooks.first != King.first - 1){
		return println("Yes")
	}

	if (OpponentKing.second == 7 && King.second == 5 && OpponentKing.first == King.first && 
		rooks.first != King.first && rooks.first != King.first + 1 && rooks.first != King.first - 1){
		return println("Yes")
	}

	if (OpponentKing == (0 to 0) && King == (2 to 1) && rooks.second != King.second){
		return println("Yes")
	}

	if (OpponentKing == (0 to 0) && King == (1 to 2) && rooks.first != King.first){
		return println("Yes")
	}

	if (OpponentKing == (0 to 7) && King == (2 to 6) && rooks.second != King.second){
		return println("Yes")
	}

	if (OpponentKing == (0 to 7) && King == (1 to 5) && rooks.first != King.first){
		return println("Yes")
	}

	if (OpponentKing == (7 to 0) && King == (5 to 1) && rooks.second != King.second){
		return println("Yes")
	}

	if (OpponentKing == (7 to 0) && King == (6 to 2) && rooks.first != King.first){
		return println("Yes")
	}

	if (OpponentKing == (7 to 7) && King == (5 to 6) && rooks.second != King.second){
		return println("Yes")
	}

	if (OpponentKing == (7 to 7) && King == (6 to 5) && rooks.first != King.first){
		return println("Yes")
	}

	println("No")
}