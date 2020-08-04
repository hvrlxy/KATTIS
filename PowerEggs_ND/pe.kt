import java.util.Scanner


fun main(){
	val opt = Array(33, {LongArray(33)})

	for (k in 0 until 33){
		opt[k][1] = k.toLong()
	}

	for (k in 2 until 33){
		for (e in 1 until 33){
			opt[k][e] = k.toLong()
			for (j in 1 until k - 1){
				opt[k][e] += opt[j][e - 1]
			}
		}
	}

	// for (k in 0 until 33){
	// 	println(opt[k].joinToString())
	// }

	println(opt[3][3])

	val sc = Scanner(System.`in`)

	val numCase = sc.nextInt()

	for (i in 0 until numCase){
		val floors = sc.nextInt()
		val eggs = sc.nextInt()

		if (floors > opt[32][eggs]){
			//println("impossible")
		}

		for (k in 2 until 33){
			if (opt[k][eggs] > floors){
				//println(k - 1)
				break
			}
		}
	}
}



