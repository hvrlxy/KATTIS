import kotlin.math.max

fun main(){
	val n = readLine()!!.toInt()
	val weightArray = IntArray(n){readLine()!!.toInt()}

	// var sortedOrder = java.util.ArrayDeque<Int>()

	// fun opt(i : Int): Int{
	// 	if (i == n) return sortedOrder.size

	// 	var insertHead = -1
	// 	var insertTail = -1

	// 	if (sortedOrder.isEmpty() || weightArray[i] < sortedOrder.getFirst()){
	// 		sortedOrder.addFirst(weightArray[i])
	// 		insertHead = opt(i + 1)
	// 		sortedOrder.removeFirst()
	// 	}

	// 	if (sortedOrder.isEmpty() || weightArray[i] > sortedOrder.getLast()){
	// 		sortedOrder.add(weightArray[i])
	// 		insertTail = opt(i + 1)
	// 		sortedOrder.removeLast()
	// 	}

	// 	val skip = opt(i + 1)

	// 	return max(insertTail, max(insertHead, skip))
	// }

	// println(opt(0))


	val a = IntArray(n + 1){0} // insert tail option
	val b = IntArray(n + 1){0} // insert head option

	for (i in n - 1 downTo 0){ // loop from the last train
		for (j in i + 1 .. n){ // loop from the first train to train i
			if (j == n || weightArray[j] > weightArray[i]){ 
			// if the train j is heavier than train i, or the train j is the last train,
			// we insert train i to the tail of train j, or we can skip train j
				a[i] = max(a[i], a[j] + 1)
			}
			if (j == n || weightArray[j] < weightArray[i]){
				// if train j is lighter than train i, or train j is the last train, we can
				// insert train i to the head of train j, or skip train j
				b[i] = max(b[i], b[j] + 1)
			}
		}
	}

	var max = 0

	for (i in 0 until n){
		max = max(max, a[i] + b[i] - 1) // add the train we insert to the front and to the left 
		// of train i, minus 1 because we count train i twice during the process
	}
	println(max)
}