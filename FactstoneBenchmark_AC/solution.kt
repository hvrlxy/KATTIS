import kotlin.math.log10

fun main(){
	var n = readLine()!!.toInt()
	while (n != 0){
		var w = log10(4.0)
		for (i in 1960 .. n step 10){
			w *= 2
		}

		var f = 0.0
		var i = 1 //counting the factorial
		while (f < w){
			i++
			f += log10(i.toDouble())
		}
		println(i - 1)
		n = readLine()!!.toInt()
	}
}