import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(System.`in`)

	val num1s = sc.nextInt()
	val num0s = sc.nextInt()

	val aString = (sc.next()).reversed()
	val bString = sc.next()

	val time = sc.nextInt()

	val simulationArray = IntArray(num1s + num0s){0}
	for (i in 0 until num1s){
		simulationArray[i] = 1
	}

	for (i in 0 until time){
		swap(simulationArray)
	}

	var result = ""
	var a = 0
	var b = 0
	for (i in 0 until (num0s + num1s)){
		if (simulationArray[i] == 1){
			result += aString[a]
			a ++
		}
		else{
			result += bString[b]
			b++
		}
	}
	println(result)
}

fun swap(x:IntArray): IntArray{
	var i = 0
	while (i <= x.size - 2){
		if ((x[i] == 1) && (x[i + 1] == 0)){
			x[i] = 0
			x[i + 1] = 1
			i +=2
		}
		else{
			i ++
		}
	}

	return x
}