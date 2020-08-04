import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	val numCase = sc.nextInt()
	repeat(numCase){
		val numOrders = sc.nextInt()
		val array = mutableMapOf<String, Int>()
		repeat(numOrders){
			val toy = sc.next()
			val num = sc.nextInt()
			if (toy in array.keys){
				val c = array[toy]!!
				array[toy] = c + num
			}
			else{
				array[toy] = num
			}
		}

		val list = array.keys.toMutableList()
		list.sortDescending()
		list.sortBy{array[it]!!}
		list.reverse()
		println(list.size)
		for (i in list){
			println("$i ${array[i]}")
		}
	}
}