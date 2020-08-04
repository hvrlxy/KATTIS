import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	var caseNo = 0
	while (sc.hasNextInt()){
		caseNo ++
		val a = sc.nextInt()
		val b = sc.nextInt()
		val c = sc.nextInt()
		val d = sc.nextInt()


		val determinant = 1/(a*d - b*c)
		val newa = determinant * d 
		val newb = determinant * -b
		val newc = determinant * -c
		val newd = determinant * a

		println("Case $caseNo:\n$newa $newb\n$newc $newd")
	}
}