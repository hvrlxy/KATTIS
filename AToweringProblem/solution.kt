import java.util.Scanner
import java.io.*

fun main (args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val boxArray = IntArray(6){0}

	for (i in 0 .. 5){
		boxArray[i] = sc.nextInt()
	}

	val h1 = sc.nextInt()
	val h2 = sc.nextInt()

	val tower1 = findThree(boxArray, h1)
	val tower2 = IntArray(3){0}

	var k = 0
	for (i in 0 .. 5){
		if (boxArray[i] !in tower1){
			tower2[k] = boxArray[i]
			k ++
		}
	}

	tower1.sort()
	tower2.sort()

	println("${tower1[2]} ${tower1[1]} ${tower1[0]} ${tower2[2]} ${tower2[1]} ${tower2[0]}")
}

fun findThree (x: IntArray, h1: Int): IntArray{
	val tower1 = IntArray(3){0}
	for (i in 0 .. 3){
		tower1[0] = x[i]
		for (j in (i + 1) .. 4){
			tower1[1] = x[j]
			for (k in (j + 1) .. 5){
				tower1[2] = x[k]
				if (tower1.sum() == h1){
					return tower1
				}
			}
		}
	}
	return tower1
}