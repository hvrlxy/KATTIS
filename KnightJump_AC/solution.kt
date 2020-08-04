import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val size = sc.nextInt()

	val board = KnightGraph(size)
	val unusedArray = mutableListOf<Int>()
	var source = 0

	var k = 0
	for (i in 0 until size){
		val str = sc.next()
		for (j in 0 until size){
			if (str[j] == '#'){
				unusedArray.add(k)
			}
			else if (str[j] == 'K'){
				source = k
			}
			k ++
		}
	}

	val disc = IntArray(size * size){-1}
	disc[source] = 0

	val queue = mutableListOf<Int>()
	queue.add(source)

	while (queue.isNotEmpty()){
		val v = queue[0]
		queue.removeAt(0)
		for (j in board[v]){
			if (j !in unusedArray && disc[j] == -1){
				disc[j] = disc[v] + 1
				queue.add(j)
			}
		}
	}

	println(disc[0])

}

fun KnightGraph(size: Int): Array<MutableList<Int>>{
	val n = size * size

	val board = Array<MutableList<Int>>(n){mutableListOf<Int>()}

	for (i in 0 until n){
		if ((i % size) < (size - 1) && i > (size * 2 - 1) && (i - (size * 2 - 1)) >= 0){
			board[i].add(i - (size * 2 - 1))
		}
		if ((i % size) > 1 && i > (size - 1) && (i - size - 2) >= 0){
			board[i].add(i - size - 2)
		}
		if ((i % size) > 0 && i > (size * 2 - 1) && (i - size * 2 - 1) >= 0){
			board[i].add(i - size * 2 - 1)
		}
		if ((i % size) < (size - 2) && i > (size - 1) && (i - size + 2) >= 0){
			board[i].add(i - size + 2)
		}
		if ((i % size) > 1 && i < (n - size) && (i + size - 2) <= n){
			board[i].add(i + size - 2)
		}
		if ((i % size) < (size - 2) && i < (n - size) && (i + size + 2) <= n){
			board[i].add(i + size + 2)
		}
		if ((i % size) > 0 && i < (n - size * 2) && (i + size * 2 - 1) <= n){
			board[i].add(i + size * 2 - 1)
		}
		if ((i % size) < (size - 1) && i < (n - size * 2) && (i + size * 2 + 1) <= n){
			board[i].add(i + size * 2 + 1)
		}
	}

	return board
}