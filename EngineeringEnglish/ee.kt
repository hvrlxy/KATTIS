import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	var result = ""
	var resultUppercase = mutableListOf<String>()

	while (sc.hasNext()){
		val word = sc.next()

		val wordUppercase = word.toUpperCase()

		if (wordUppercase !in resultUppercase){
			result += "$word "
			resultUppercase.add(wordUppercase)
		}
		else{
			result += ". "
		}
	}

	println(result.slice(0..result.length - 2))
}