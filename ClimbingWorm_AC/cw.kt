import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	val up = sc.nextInt()
	val down = sc.nextInt()

	val h = sc.nextInt()

	if (up >= h){
		println(1)
		return
	}

	val d = up - down
	
	if ((h - up)%d == 0){
		println((h - up)/d + 1)
	}
	else{
		println((h - up)/d + 2)
	}
}