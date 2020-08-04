import java.util.Scanner
import java.io.*

fun main(args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	var num = sc.nextInt()
	var array = mutableListOf<Int>()

	var i = 1
	while (i <= (num).toInt()){
		if (num % (i + 1) == 0){
			num /= (i + 1)
			array.add(i)
			i++
		}
	}

	println(array.size)
}