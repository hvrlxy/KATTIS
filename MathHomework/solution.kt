import java.util.Scanner
import java.io.*

fun main (args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val leg1 = sc.nextInt()
	val leg2 = sc.nextInt()
	val leg3 = sc.nextInt()

	val sumLegs = sc.nextInt()

	val max1 = sumLegs / leg1
	val max2 = sumLegs / leg2
	val max3 = sumLegs / leg3

	val array = IntArray(3){0}

	var count = 0

	for (i in 0 .. max1){
		array[0] = i
		for (j in 0 .. max2){
			array[1] = j
			for (k in 0 .. max3){
				array[2] = k

				if (array[0] * leg1 + array[1] * leg2 + array[2] * leg3 == sumLegs){
					println("${array[0]} ${array[1]} ${array[2]}")
					count ++
				}
			}
		}
	}

	if (count == 0){
		println("impossible")
	}
}