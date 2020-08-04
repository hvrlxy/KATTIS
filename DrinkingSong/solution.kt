fun main(){
	val num = readLine()!!.toInt()
	val beverage = readLine()!!

	for (i in 0 until num){
		if (i < num - 2){
			println("${num - i} bottles of $beverage on the wall, ${num - i} bottles of $beverage.\nTake one down, pass it around, ${num - i - 1} bottles of $beverage on the wall.")
			println()
		}
		else if (i == num - 2){
			println("2 bottles of $beverage on the wall, 2 bottles of $beverage.\nTake one down, pass it around, 1 bottle of $beverage on the wall.")
			println()
		}
		else{
			println("1 bottle of $beverage on the wall, 1 bottle of $beverage.\nTake it down, pass it around, no more bottles of $beverage.")
		}
	}
}