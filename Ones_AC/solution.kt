fun main(){
	var n = readLine()

	while (n != null){
		val a = n.toInt()

		var mod = 1 % a
		var currentMod = 1 % a
		var result = 1
		while (mod != 0){
			result ++
			currentMod = (currentMod * 10) % a
			mod = (mod + currentMod) % a
			//println(mod)
		}
		println(result)
		n = readLine()
	}
}