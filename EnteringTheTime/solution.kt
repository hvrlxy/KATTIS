//import kotlin.math.abs

fun main(){
	val aString = readLine()!!
	val bString = readLine()!!

	var a = aString[0].toString().toInt()
	var b = aString[1].toString().toInt()
	var c = aString[3].toString().toInt()
	var d = aString[4].toString().toInt()

	val A = bString[0].toString().toInt()
	val B = bString[0].toString().toInt()
	val C = bString[0].toString().toInt()
	val D = bString[0].toString().toInt()

	val timeList = mutableListOf<String>(aString)

	// first transform d to D:

	if (d > D){
		if (d - D < D + 10 - d){
			while (d != D){
				d = (d + 1) % 10
				println("$a$b:$c$d")
			}
		}
		else{
			while (d != D){
				d = (d - 1 + 10) % 10
				println("$a$b:$c$d")
			}
		}
	}
	else{
		if (D - d < d + 10 - D){
			while (d != D){
				d = (d - 1 + 10) % 10
				println("$a$b:$c$d")
			}
		}
		else{
			while (d != D){
				d = (d + 1) % 10
				println("$a$b:$c$d")
			}
		}
	}

	//transform c to C:

	if (c > C){
		if (c - C < C + 7 - c){
			while (c != C){
				c = (c + 1) % 7
				println("$a$b:$c$d")
			}
		}
		else{
			while (c != C){
				c = (c - 1 + 7) % 7
				println("$a$b:$c$d")
			}
		}
	}
	else{
		if (C - c < c + 7 - C){
			while (c != C){
				c = (c - 1 + 7) % 7
				println("$a$b:$c$d")
			}
		}
		else{
			while (c != C){
				c = (c + 1) % 7
				println("$a$b:$c$d")
			}
		}
	}

	//transform a to A:

	if (a > A){
		if (a - A < A + 3 - a){
			while (a != A){
				a = (a + 1) % 3
				println("$a$b:$c$d")
			}
		}
		else{
			while (a != A){
				a = (a - 1 + 3) % 3
				println("$a$b:$c$d")
			}
		}
	}
	else{
		if (A - a < a + 3 - A){
			while (a != A){
				a = (a - 1 + 3) % 3
				println("$a$b:$c$d")
			}
		}
		else{
			while (a != A){
				a = (a + 1) % 3
				println("$a$b:$c$d")
			}
		}
	}

	//transform b to B

	if (a == 2){
		if (b > B){
			if (b - B < B + 4 - b){
				while (b != B){
					b = (b + 1) % 4
					println("$a$b:$c$d")
				}
			}
			else{
				while (b != B){
					b = (b - 1 + 4) % 4
					println("$a$b:$c$d")
				}
			}
		}
		else{
			if (B - b < b + 4 - B){
				while (b != B){
					b = (b - 1 + 4) % 4
					println("$a$b:$c$d")
				}
			}
			else{
				while (b != B){
					b = (b + 1) % 4
					println("$a$b:$c$d")
				}
			}
		}
	}
	else{
		if (b > B){
			if (b - B < B + 10 - b){
				while (b != B){
					b = (b + 1) % 10
					println("$a$b:$c$d")
				}
			}
			else{
				while (b != B){
					b = (b - 1 + 10) % 10
					println("$a$b:$c$d")
				}
			}
		}
		else{
			if (B - b < b + 10 - B){
				while (b != B){
					b = (b - 1 + 10) % 10
					println("$a$b:$c$d")
				}
			}
			else{
				while (b != B){
					b = (b + 1) % 10
					println("$a$b:$c$d")
				}
			}
		}
	}

	timeList.add(bString)

	println(timeList.size)
	//println(timeList.joinToString(separator = "\n"))
}	