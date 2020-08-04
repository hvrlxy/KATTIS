import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	var numHeads = sc.nextInt()
	var numKnights = sc.nextInt()
	while(numHeads + numKnights != 0){
		val headArray = IntArray(numHeads){0}
		val knightArray = IntArray(numKnights){0}

		for (i in 0 until numHeads){
			headArray[i] = sc.nextInt()
		}
		for (i in 0 until numKnights){
			knightArray[i] = sc.nextInt()
		}

		headArray.sort()
		knightArray.sort()

		var i = 0
		var j = 0
		var result = 0
		var resultCoin = 0
		while (i < numKnights && j < numHeads){
			if (knightArray[i] >= headArray[j]){
				resultCoin += knightArray[i]
				i++
				j++
				result++
			}
			else{
				i++
			}
		}
		if (result < numHeads) println("Loowater is doomed!") else println(resultCoin)
		numHeads = sc.nextInt()
		numKnights = sc.nextInt()
	}
}