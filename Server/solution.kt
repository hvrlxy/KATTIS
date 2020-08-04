import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	var numTasks = sc.nextInt()
	val min = sc.nextInt()
	var result = 0
	var total = 0
	total += sc.nextInt()
	while (total <= min){
		numTasks -- 
		result ++
	if (sc.hasNext()) total += sc.nextInt() else break
	}
	println(result)
}