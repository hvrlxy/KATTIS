import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)
	val numRooms = sc.nextInt()
	val bookedRoom = sc.nextInt()
	if (numRooms == bookedRoom) return println("too late")
	val array = BooleanArray(numRooms){false}
	repeat(bookedRoom){
		array[sc.nextInt() - 1] = true
	}
	for (i in 0 until numRooms){
		if (array[i] == false) return println(i + 1)
	}
}