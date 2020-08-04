fun main(){
	val numCase = readLine()!!.toInt()
	repeat(numCase){
		val a = readLine()!!.split(" ").map{ it.toInt() }

		var result = 0
		var idx = 0

		val array = mutableListOf<Int>()
		while (idx < a.size - 1){
			idx ++
			for (num in array){
				if (num > a[idx]) result ++
			}
			array.add(a[idx])
		}

		println("${a[0]} $result")
	}
}