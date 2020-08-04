import java.util.Scanner
import java.io.*

data class Queen (var x: Int, var y: Int)

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numQueens = sc.nextInt()

	val rowSet = mutableSetOf<Int>()
	val columnSet = mutableSetOf<Int>()

	val SWdiagonal = mutableSetOf<Int>()
	val NEdiagonal = mutableSetOf<Int>()

	fun isValid(q: Queen): Boolean{
		if (q.x in rowSet || q.y in columnSet || (q.x - q.y) in SWdiagonal || (q.x + q.y) in NEdiagonal) return false
		return true
	}

	for (i in 0 until numQueens){
		val q = Queen(sc.nextInt(), sc.nextInt())
		if (!isValid(q)) return println("INCORRECT")
		rowSet.add(q.x)
		columnSet.add(q.y)
		SWdiagonal.add(q.x - q.y)
		NEdiagonal.add(q.x + q.y)
	}
	println("CORRECT")
}