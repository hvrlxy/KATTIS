fun convert(s: String): String{
	var seq = s.split("").toMutableList()
	seq.removeAt(0)
	val idx = listOf(seq.indexOf("a"),seq.indexOf("o"),seq.indexOf("u"),seq.indexOf("i"),seq.indexOf("e"),seq.indexOf("y")).filter{it > -1}
	if (idx.min()!! > 0){
		return ("${s.slice(idx.min()!! .. (s.length - 1))}${s.slice(0 .. idx.min()!! - 1)}ay")
	}
	return ("${s}yay")
}

fun main(){
	var a = readLine()
	while (a != null){
		println(a.split(" ").map{convert(it)}.joinToString(separator = " "))
		a = readLine()
	}
}