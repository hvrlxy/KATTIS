import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	repeat(sc.nextInt()){
		val array = IntArray(sc.nextInt()){sc.nextInt()}
		if (array.count{ it == array.max()!!} > 1) println("no winner")
		else if (array.max()!! > array.sum()/2) println("majority winner ${array.indexOf(array.max()!!) + 1}")
		else println("minority winner ${array.indexOf(array.max()!!) + 1}")
	}
}