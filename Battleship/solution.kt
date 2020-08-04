import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()
	repeat(numCase){
		val stats = rd.readInts()
		val player1Ships = mutableSetOf<Pair<Int, Int>>()
		val player2Ships = mutableSetOf<Pair<Int, Int>>()

		for (i in 0 until stats[1]){
			val aString = rd.readLn()
			for (j in 0 until stats[0]){
				if (aString[j] == '#') player1Ships.add(j to i)
			}
		}

		for (i in 0 until stats[1]){
			val aString = rd.readLn()
			for (j in 0 until stats[0]){
				if (aString[j] == '#') player2Ships.add(j to i)
			}
		}

		//println(player1Ships)
		//println(player2Ships)

		var isPlayer1Turn = true
		for (i in 0 until stats[2]){
			if (isPlayer1Turn){
				val a = rd.readInts()
				if ((a[0] to a[1]) in player2Ships) {
					player2Ships.remove (a[0] to a[1])
				}
			}
			else{
				val a = rd.readInts()
				if ((a[0] to a[1]) in player1Ships) {
					player1Ships.remove (a[0] to a[1])
				}
			}

			isPlayer1Turn = !isPlayer1Turn
		}

		if (player1Ships.isEmpty() && player2Ships.isEmpty()) println("draw")
		else if (player1Ships.isEmpty()) println("player two wins")
		else println("player one wins")
	}
}