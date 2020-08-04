import java.util.Scanner
import kotlin.math.sqrt

fun main(){
	val sc = Scanner(System.`in`)

	val N = sc.nextInt()
	val M = sc.nextInt()
	val K = sc.nextInt()

	val array1 = FloatArray(N){0f}
	for (i in 0 until N){
		array1[i] = sc.nextFloat()
	}

	val array2 = FloatArray(M + K){0f}
	for (i in 0 until M){
		array2[i] = sc.nextFloat()
	}

	fun findR(x: Int): Float{
		return (sqrt(2F) * x / 2f)
	}

	for (i in 0 until K){
		array2[i + M] = findR(sc.nextInt())
	}

	array1.sort()
	array2.sort()
	var result = 0
	var i = 0
	var j = 0
	while (i < N && j < M + K){
		if (array1[i] > array2[j]){
			result ++
			i ++
			j ++
		}
		else{
			i ++
		}
	}

	println(result)
}