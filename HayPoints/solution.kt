import java.util.Scanner
import java.io.*

fun main(){
    val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

    val numWords = sc.nextInt()
    val numJobs = sc.nextInt()
    
    val dictionary = mutableMapOf<String, Int>()

    for (i in 0 until numWords){
        dictionary[sc.next()] = sc.nextInt()
    }

    for (i in 0 until numJobs){
        val wordList = mutableListOf<String>()

        var words = sc.next()
        while (words != "."){
            wordList.add(words)
            words = sc.next()
        }
        
        var result = 0
        for (i in 0 until wordList.size){
            if (dictionary.containsKey(wordList[i])){
                result += dictionary[wordList[i]!!]!!
            }
        }

        println(result)
    }
}
