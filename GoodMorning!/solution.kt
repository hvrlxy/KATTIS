import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCase = sc.nextInt()

	for (i in 0 until numCase){
		val array = mutableListOf(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,25,26,28,29,
	33,36,39,44,45,46,47,48,49,40,55,56,58,59,50,66,69,77,78,79,70,88,89,80,99,100,111,112,113,114,
	115,116,117,118,119,110,122,123,125,126,128,129,120,133,136,139,144,147,145,146,149,148,140,
	150,155,156,158,159,166,169,170,177,178,179,188,189,180,199,200)
		val num = sc.nextInt()
		if (num in array){
			println(num)
		}
		else {
			array.add(num)
			array.sort()
			val index = array.indexOf(num)
			if (index == 0){
				println(array[1])
			}
			else if (index == array.size - 1){
				println(array[array.size - 2])
			}
			else{
				if ((array[index] - array[index - 1]) <= (array[index + 1] - array[index])){
					println(array[index - 1])
				}
				else{
					println(array[index + 1])
				}
			}
		}
	}
}