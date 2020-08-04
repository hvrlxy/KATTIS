import java.util.Scanner
import kotlin.math.*

fun main(){
	val sc = Scanner(System.`in`)

	val O = sc.nextInt()
	val I = sc.nextInt()

	//2X + 2Y - 4 = O
	//XY - O = I 
	//X + Y = (O + 4)/2 && X = (O + I)/Y -> (O + I) + Y^2 = (O + 4) * Y/2
	//2 * Y^2 - (O + 4)/2 * Y - (I + O) = 0

	var Y = 1
	while(2F * (I + O)/ Y + Y * 2F - 4F != O.toFloat()){
		Y ++
	}

	val X = (O + I)/Y
	println("${max(X, Y)} ${min(X, Y)}")
}