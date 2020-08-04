import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numPlayer = sc.nextInt()
	val minScore = sc.nextInt()
	val m = sc.nextInt()

	val aMap = mutableMapOf<String, Int>()
	val PlayerArray = Array<String>(numPlayer){""}

	for (i in 0 until numPlayer){
		PlayerArray[i] = sc.next()
		aMap[PlayerArray[i]] = 0
	}

	for (i in 0 until m){
		val player = sc.next()
		var current = aMap[player]!!
		current += sc.nextInt()
		aMap[player] = current
	}

	var winners = 0
	for (i in 0 until numPlayer){
		if (aMap[PlayerArray[i]]!! >= minScore){
			println("${PlayerArray[i]} wins!")
			winners ++
		}
	}

	if(winners == 0){
		println("No winner!")
	}
}