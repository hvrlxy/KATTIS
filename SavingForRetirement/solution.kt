import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	val BobAge = sc.nextLong()
	val BobRetire = sc.nextLong()
	val BobSave = sc.nextLong()

	val b = BobSave * (BobRetire - BobAge)

	val AliceAge = sc.nextLong()
	val AliceSave = sc.nextLong()

	val yearsTillRetirement = b / AliceSave + 1L
	println(yearsTillRetirement + AliceAge)

	// if (yearsTillRetirement * AliceSave <= BobSave) println(yearsTillRetirement + AliceAge + 1L) else println(yearsTillRetirement + AliceAge)
}