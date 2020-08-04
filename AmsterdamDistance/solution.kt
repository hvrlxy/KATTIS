import java.util.Scanner
import java.io.*
import kotlin.math.*

fun main (args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))
	
	val M = sc.nextInt()
	val N = sc.nextInt()
	val R = sc.nextFloat()

	// val distInM : Double = R * PI / M
	val distInN : Float = R / N

	val aX = sc.nextInt()
	val aY = sc.nextInt()
	val bX = sc.nextInt()
	val bY = sc.nextInt()
	//println(aY)
	//println(bY)

	val dM = abs(aX - bX)
	val dN = abs(aY - bY)
	//println(dN)

	val verticalDist = (dN * distInN)
	//println (verticalDist)

	val horizontalDist = (((min(aY, bY) / N.toFloat()) * R) * PI) * (dM.toFloat() / M)
	//println (min(aY, bY) / N.toFloat())
	val option1 = (verticalDist + horizontalDist)

	val option2 = ((aY/N.toFloat() + bY/N.toFloat()) * R)
	println(min(option2, option1.toFloat()))
}