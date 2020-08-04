import java.util.Scanner
import java.io.*

fun main(args: Array<String>){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))
	val opt = Array(4, {IntArray(4)})

	for (i in 0 ..3){
		for (j in 0 .. 3){
			opt[i][j] = sc.nextInt()
		}
	}

	val direction = sc.nextInt()

	if (direction == 0){
		for (i in 0 ..3){
			for (j in 0..2){
				if (opt[i][j] == 0){
					for (k in j+1 ..3){
						opt[i][j] = opt[i][k] // move each element inside the array 1 position ahead
					}
					opt[i][3] = 0
				}
			}
		}

		for (i in 0..3){
			for (j in 0..2){
				if (opt[i][j] == opt[i][j + 1]){
					opt[i][j] = opt[i][j] + opt[i][j+1]
					for (k in j + 1 until 3){
						opt[i][k] = opt[i][k + 1] // move each element inside the array 1 position ahead
					}
					if (opt[i][0] == 0){
						for (a in 0 ..2){
							opt[i][a] = opt[i][a+1]
						}
					}
					opt[i][3] = 0
				}
			}
		}
	}


	for (i in 0 until 4){
		println(opt[i].joinToString())
	}
}