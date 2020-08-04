import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val row = sc.nextInt()
	val col = sc.nextInt()

	var day = mutableListOf<Pair<Int,Int>>()
	var afterDay = mutableListOf<Pair<Int,Int>>()

	repeat(sc.nextInt()){
		val pos = (sc.nextInt() to sc.nextInt())
		if (pos !in day) day.add(pos)
	}

	fun addPos(x: Int, y: Int){
		if (x > 1 && (x - 1 to y) !in afterDay) afterDay.add(x - 1 to y)
		if (x < col && (x + 1 to y) !in afterDay) afterDay.add(x + 1 to y)
		if (y > 1 && (x to y - 1) !in afterDay) afterDay.add(x to y - 1)
		if (y < row && (x to y + 1) !in afterDay) afterDay.add(x to y + 1)
	}

	var count = 1
	while (day.size < row * col){
		for (i in day){
			afterDay.add(i)
			addPos(i.first, i.second)
		}
		day = afterDay
		afterDay = mutableListOf<Pair<Int,Int>>()
		count ++ 
	}

	println(count)
}