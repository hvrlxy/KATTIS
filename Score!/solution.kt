import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numRecordings = rd.readInt()

	var currenSeconds = 0
	var Hpoints = 0
	var Apoints = 0
	var Hleads = 0
	var Aleads = 0

	repeat(numRecordings){
		val line = rd.readStrings()
		val time = line[2].split(":").map{it.toInt()}

		if (Hpoints > Apoints) Hleads += (time[0] * 60 + time[1]) - currenSeconds
		else if (Hpoints < Apoints) Aleads += (time[0] * 60 + time[1]) - currenSeconds

		currenSeconds += (time[0] * 60 + time[1]) - currenSeconds

		//println(Hleads)

		if (line[0] == "H") Hpoints += line[1].toInt() else Apoints += line[1].toInt()
	}

	if (Hpoints > Apoints) Hleads += 1920 - currenSeconds
	else if (Hpoints < Apoints) Aleads += 1920 - currenSeconds

	val HMin = (Hleads / 60).toString()
	val Hsecond = (Hleads - (Hleads / 60) * 60).toString()
	val AMin = (Aleads / 60).toString()
	val Asecond = (Aleads - (Aleads / 60) * 60).toString()

	if (Hpoints > Apoints) println("H $HMin:${"0".repeat(2 - Hsecond.length)}$Hsecond $AMin:${"0".repeat(2 - Asecond.length)}$Asecond")
	else println("A $HMin:${"0".repeat(2 -Hsecond.length)}$Hsecond $AMin:${"0".repeat(2 - Asecond.length)}$Asecond")
}