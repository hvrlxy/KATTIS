import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toList() // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()
	repeat(numCase){
		val num = rd.readInt()

		fun findBiggest(n: Int): String{
			if (n % 2 == 0) return "1".repeat(n / 2)
			else return "7" + "1".repeat(n / 2 - 1)
		}

		fun findSmallest(n: Int): String{
			val numEights = n / 7
			//println("numEights: $numEights")
			if (n % 7 == 0) return "8".repeat(numEights)
			else if (n % 7 == 1) return "10" + "8".repeat(numEights - 1)
			else if (n % 7 == 2) return "1" + "8".repeat(numEights)
			else if (n % 7 == 3) {
				if (numEights == 0 )return "7" + "8".repeat(numEights) 
				else if (numEights == 1) return "22" + "8".repeat(numEights - 1)
				else return "200" + "8".repeat(numEights - 2)
			}
			else if (n % 7 == 4) {
				if (numEights == 0)return "4" + "8".repeat(numEights) else return "20" + "8".repeat(numEights - 1)
			}
			else if (n % 7 == 5) return "2" + "8".repeat(numEights)
			else return "6" + "8".repeat(numEights)
		}	

		println("${findSmallest(num)} ${findBiggest(num)}")
	}
}