fun main(){
	val numTrue = readLine()!!.toInt()
	val friend = readLine()!!
	val myanswer = readLine()!!

	var similarAns = 0
	for (i in 0 until friend.length){
		if (friend[i] == myanswer[i]) similarAns ++
	}

	if (numTrue > similarAns) println(friend.length - numTrue + similarAns)
	else println(numTrue + friend.length - similarAns)
}