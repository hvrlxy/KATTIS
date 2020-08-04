import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	val num = sc.nextInt()
	val d = sc.nextInt()

	val aMap = mutableMapOf<Int, Int>()

	for (i in 0 until num){
		val a = sc.nextInt()
		if (a/d !in aMap){
			aMap[a/d] = 1
		}
		else{
			val c = aMap[a/d]!!
			aMap[a/d] = c + 1
		}
	}

	fun nChooseK(n: Int, k: Int): Long{ 
		var result = 1L 
		if (k <= n/2L) { 
			for (i in 1L .. k.toLong()){ 
				result = result * (n - i + 1) / i 
			} 
		} 
		else{ 
			result = nChooseK(n, n - k) 
		} 
		return result 
	}

	var result = 0L

	for (i in aMap.values){
		if (i >= 2) result += nChooseK(i, 2)
	}

	println(result)
}

