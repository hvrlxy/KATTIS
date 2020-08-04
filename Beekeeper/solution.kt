import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	var numWords = sc.nextInt()
	while(numWords != 0){
		var countDoubleVowel = 0
		var word = ""
		for (i in 0 until numWords){
			val word1 = sc.next()
			val word1Vowel = word1.count('ee') + word1.count('aa') + word1.count('ee') + word1.count('ii') + word1.count('oo')
			if (word1Vowel){
				word = word1
				countDoubleVowel = word1Vowel 
			}
		}

		println(word)
		numWords = sc.nextInt()
	}
}