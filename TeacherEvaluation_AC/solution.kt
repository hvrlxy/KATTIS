import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	val numScores = sc.nextInt()

	val P = sc.nextInt()

	var sum = 0
	for (i in 0 until numScores){
		sum += sc.nextInt()
	}

	var result = 0
	if (P != 100) result = ((P * numScores - sum) / (100 - P))

	if (P == 100) return println("impossible")
	if (sum + 100 * result < P * (numScores + result)) result ++
	println(result)
}