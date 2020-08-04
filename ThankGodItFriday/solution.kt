fun main(){
	val aLine =readLine()!!.split(" ")

	val today = aLine[0].toInt()
	val month = aLine[1]

	val janFirst = readLine()!!

	val firstFriday : Int

	if (janFirst == "MON") firstFriday = 5
	else if (janFirst == "TUE") firstFriday = 4
	else if (janFirst == "WED") firstFriday = 3
	else if (janFirst == "THU") firstFriday = 2
	else if (janFirst == "FRI") firstFriday = 1
	else if (janFirst == "SAT") firstFriday = 6
	else firstFriday = 7

	val notLeap : Int 
	val isLeap : Int 

	if (month == "JAN"){
		if ((today - firstFriday) % 7 != 0) return println(":(") else return println("TGIF")
	}

	if (month == "FEB"){
		if ((today + 31 - firstFriday) % 7 != 0) return println(":(") else return println("TGIF")
	}

	if (month == "MAR"){
		notLeap = today + (31 + 28)
		isLeap = today + (31 + 29)
		if ((notLeap - firstFriday) % 7 == 0 || (isLeap - firstFriday) % 7 == 0) return println("not sure") else return println(":(")
	}

	if (month == "APR"){
		notLeap = today + (31 + 28 + 31)
		isLeap = today + (31 + 29 + 31)
		if ((notLeap - firstFriday) % 7 == 0 || (isLeap - firstFriday) % 7 == 0) return println("not sure") else return println(":(")
	}

	if (month == "MAY"){
		notLeap = today + (31 + 28 + 31 + 30)
		isLeap = today + (31 + 29 + 31 + 30)
		if ((notLeap - firstFriday) % 7 == 0 || (isLeap - firstFriday) % 7 == 0) return println("not sure") else return println(":(")
	}

	if (month == "JUN"){
		notLeap = today + (31 + 28 + 31 + 30 + 31)
		isLeap = today + (31 + 29 + 31 + 30 + 31)
		if ((notLeap - firstFriday) % 7 == 0 || (isLeap - firstFriday) % 7 == 0) return println("not sure") else return println(":(")
	}

	if (month == "JUL"){
		notLeap = today + (31 + 28 + 31 + 30 + 31 + 30)
		isLeap = today + (31 + 29 + 31 + 30 + 31 + 30)
		if ((notLeap - firstFriday) % 7 == 0 || (isLeap - firstFriday) % 7 == 0) return println("not sure") else return println(":(")
	}

	if (month == "AUG"){
		notLeap = today + (31 + 28 + 31 + 30 + 31 + 30 + 31)
		isLeap = today + (31 + 29 + 31 + 30 + 31 + 30 + 31)
		if ((notLeap - firstFriday) % 7 == 0 || (isLeap - firstFriday) % 7 == 0) return println("not sure") else return println(":(")
	}

	if (month == "SEP"){
		notLeap = today + (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31)
		isLeap = today + (31 + 29 + 31 + 30 + 31 + 30 + 31 + 31)
		if ((notLeap - firstFriday) % 7 == 0 || (isLeap - firstFriday) % 7 == 0) return println("not sure") else return println(":(")
	}

	if (month == "OCT"){
		notLeap = today + (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30)
		isLeap = today + (31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30)
		if ((notLeap - firstFriday) % 7 == 0 || (isLeap - firstFriday) % 7 == 0) return println("not sure") else return println(":(")
	}

	if (month == "NOV"){
		notLeap = today + (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31)
		isLeap = today + (31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31)
		if ((notLeap - firstFriday) % 7 == 0 || (isLeap - firstFriday) % 7 == 0) return println("not sure") else return println(":(")
	}

	if (month == "DEC"){
		notLeap = today + (31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31)
		isLeap = today + (31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31)
		if ((notLeap - firstFriday) % 7 == 0 || (isLeap - firstFriday) % 7 == 0) return println("not sure") else return println(":(")
	}
}