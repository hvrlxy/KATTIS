import java.util.Scanner
import kotlin.math.*

var array = mutableListOf(1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049, 177147, 531441, 
    1594323, 4782969, 14348907, 43046721, 129140163, 387420489)

fun main(){
    val sc = Scanner(System.`in`)

    val numCases = sc.nextInt()

    for (i in 0 until numCases){
        var weights = sc.nextInt()

        val leftPan = mutableListOf<Int>()
        val rightPan = mutableListOf<Int>()
        
        leftPan.add(weights)

        while (leftPan.sum() != rightPan.sum()){
            val a = find(abs(leftPan.sum() - rightPan.sum()))
            if (leftPan.sum() > rightPan.sum()) {
                rightPan.add(a)
            }
            else {
                leftPan.add(a)
            }
            println(a)
        }

        leftPan.removeAt(0)

        println(leftPan.joinToString())
        println(rightPan.joinToString())

        array = mutableListOf(1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049, 177147, 531441, 
    1594323, 4782969, 14348907, 43046721, 129140163, 387420489)
    }
}

fun find (num: Int): Int{
    var result = Int.MAX_VALUE
    for (i in 0 until array.size){
        if (abs(num - array[i]) < result){
            result =  array[i]
        }
    }
    array.remove(result)
    return result
}
