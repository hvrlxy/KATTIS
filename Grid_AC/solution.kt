import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val row = sc.nextInt()
	val col = sc.nextInt()
	val size = row * col
	//println(size)

	val grid = Array<MutableList<Int>>(size){mutableListOf<Int>()}


	var k = 0
	for (i in 0 until row){
		val str = sc.next()
		for (j in 0 until col){
			val direction = str[j].toString().toInt()
			//println("$k col * direction: ${col * direction} size - col * direction: ${size - col * direction}")
			if (k >= col * direction && k != k - col * direction){
				// Direction 1: up, only applicable if it is stricly bigger than col * n

				grid[k].add(k - col * direction)
			}
			if (k < size - col * direction && k != k + col * direction){
				// Direction 2: down, only applicable if it is stricly smaller than size - col * direction
				
				grid[k].add(k + col * direction)
			}

			if (k % col >= direction && k != k - direction){
				// Direction 3: left, only applicable if k % col > direction

				grid[k].add(k - direction)
			}

			if(col - k % col > direction && k != k + direction){
				// Direction 4: right, only applicable if col - k % col <= col - direction

				grid[k].add(k + direction)
			}

			k++
		}
	}

	val disc = IntArray(size){-1}

	disc[0] = 0
	val queue = mutableListOf<Int>()

	queue.add(0)
	while (queue.isNotEmpty()){
		val v = queue[0]
		queue.removeAt(0)
		for (i in grid[v]){
			//println(i)
			if (disc[i] == -1){
				disc[i] = disc[v] + 1
				queue.add(i)
			}
		}
		//println(disc.joinToString())
	}

	println(disc[size - 1])
}










