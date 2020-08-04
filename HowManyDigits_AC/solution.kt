import kotlin.math.*

fun main(){
	var aString = readLine()
	while (aString != null){
		val n = aString.toInt()

		println((n * log10(n / E) + log10(2 * PI * n) / 2.0).toInt() + 1)
		aString = readLine()
	}
}