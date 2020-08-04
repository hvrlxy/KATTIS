import java.util.Scanner
import java.io.*

fun main (args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))
	var numPoints = sc.nextInt()

	while (numPoints != 0){
		var StanPoints = 0
		var OliviaPoints = 0

		if (numPoints == 0){
			System.exit(-1)
		}

		val xArray = LongArray(numPoints){0L}
		val yArray = LongArray(numPoints){0L}

		for (i in 0 until numPoints){
			xArray[i] = sc.nextLong()
			yArray[i] = sc.nextLong()
		}	

		var xCoor = xArray[(numPoints - 1)/2]
		var yCoor = yArray[(numPoints - 1)/2]

		for (i in 0 until numPoints){
			if ((xArray[i] > xCoor && yArray[i] > yCoor) || (xArray[i] < xCoor && yArray[i] < yCoor)){
				StanPoints ++
			}

			if ((xArray[i] > xCoor && yArray[i] < yCoor) || (xArray[i] < xCoor && yArray[i] > yCoor)){
				OliviaPoints ++
			}
		}

		println("$StanPoints $OliviaPoints")
		numPoints = sc.nextInt()
	}
}