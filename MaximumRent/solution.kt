import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	val a = sc.nextInt()
	val b = sc.nextInt()

	val m = sc.nextInt()
	val n = sc.nextInt()

	var result = 0
	var x = 1
	while (x < m){
		val y = m - x

		if (2 * x + y >= n && a * x + b * y > result){
			result = a*x + b*y
		}
		x ++
	}

	println(result)
}