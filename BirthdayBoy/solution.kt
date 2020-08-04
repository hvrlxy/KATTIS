import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numPeople = rd.readInt()
	val dateArray = mutableListOf<Int>()

	fun findDate(m: Int, d: Int): Int{
		if (m == 10 && d >= 29) return d - 28
		if (m == 11) return 3 + d
		if (m == 12) return 3 + d + 30
		if (m == 1) return 3 + d + 30 + 31
		if (m == 2) return 3 + d + 30 + 31 + 31
		if (m == 3) return 3 + d + 30 + 31 + 31 + 28
		if (m == 4) return 3 + d + 30 + 31 + 31 + 28 + 31
		if (m == 5) return 3 + d + 30 + 31 + 31 + 28 + 31 + 30
		if (m == 6) return 3 + d + 30 + 31 + 31 + 28 + 31 + 30 + 31
		if (m == 7) return 3 + d + 30 + 31 + 31 + 28 + 31 + 30 + 31 + 30
		if (m == 8) return 3 + d + 30 + 31 + 31 + 28 + 31 + 30 + 31 + 30 + 31
		if (m == 9) return 3 + d + 30 + 31 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31
		else return (3 + d + 30 + 31 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30)
	}

	fun convertDate(d: Int): String{
		if (d <= 3) return ("10-${d + 28}")
		else if (d <= 33) {
			if (d - 3 >= 10) return ("11-${d - 3}") else return ("11-0${d - 3}")
		}
		else if (d <= 64) {
			if (d - 33 >= 10) return ("12-${d - 3 - 30}") else return ("12-0${d - 3 - 30}")
		}
		else if (d <= 95) {
			if (d - 64 >= 10) return ("01-${d - 64}") else return ("01-0${d - 64}")
		}
		else if (d <= 123) {
			if (d - 95 >= 10) return ("02-${d - 95}") else return ("02-0${d - 95}")
		}
		else if (d <= 154){ 
			if (d - 123 >= 10) return ("03-${d - 123}") else return ("03-0${d - 123}")
		}
		else if (d <= 184) {
			if (d - 154 >= 10) return ("04-${d - 154}") else return ("04-0${d - 154}")
		}
		else if (d <= 215) {
			if (d - 184 >= 10)return ("05-${d - 184}") else return ("05-0${d - 184}")
		}
		else if (d <= 245) {
			if (d - 215 >= 10) return ("06-${d - 215}") else return ("06-0${d - 215}")
		}
		else if (d <= 276) {
			if (d - 245 >= 10) return ("07-${d - 245}") else return ("07-0${d - 245}")
		}
		else if (d <= 307) {
			if (d - 276 >= 10) return ("08-${d - 276}") else return ("08-0${d - 276}")
		}
		else if (d <= 337) {
			if (d - 307 >= 10) return ("09-${d - 307}") else return ("09-0${d - 307}")
		}
		else {
			if (d - 337 >= 10) return ("10-${d - 337}") else return ("10-0${d - 337}")
		}
	}

	repeat(numPeople){
		val a = rd.readStrings()
		val a1 = a[1].split("-")
		dateArray.add(findDate(a1[0].toInt(), a1[1].toInt()))
	}

	if (dateArray.size == 1) return println(convertDate(dateArray[0]-1))

	dateArray.sort()
	var max = Int.MIN_VALUE
	var dayAfter = 0

	for (i in 0 until dateArray.size - 1){
		if (dateArray[i + 1] - dateArray[i] > max){
			dayAfter = dateArray[i + 1]
			max = dateArray[i + 1] - dateArray[i]
		}
	}

	if (dateArray[0] + 365 - dateArray[dateArray.size - 1] >= max){
		dayAfter = dateArray[0]
	}

	println(convertDate(dayAfter - 1))
}