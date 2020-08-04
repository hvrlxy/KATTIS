import java.util.Scanner
import java.io.*

fun main(){
    val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

    val numMinions = sc.nextInt()

    val array = Array(numMinions){IntArray(2){0}}

    for (i in 0 until numMinions){
        array[i][0] = sc.nextInt() //lowerbound
        array[i][1] = sc.nextInt() //upperbound 
    }

    array.sortBy({it[1]})

    var result = 1
    var temp = array[0][1]

    for (i in 0 until numMinions){
        if (array[i][0] <= temp && array[i][1] >= temp){
            result = result
        }
        else{
            result ++
            temp = array[i][1]
        }
    }

    println(result)
}