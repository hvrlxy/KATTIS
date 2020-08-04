import java.util.Scanner
import java.io.*

fun main(){
    val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

    val numSticks = sc.nextInt()

    val array = LongArray(numSticks){0}

    for (i in 0 until numSticks){
        array[i] = sc.nextLong()
    }

    // if (array.size < 3){
    //     println("impossible")
    //     return
    // }

    array.sort()

    fun testTriangle(x: Long, y: Long, z: Long): Boolean{
        if ((x + y) > z && (x + z) > y && (y + z) > x){
            return true
        }
        return false
    }

    for (i in 0 until array.size - 2){
        if (testTriangle(array[i], array[i + 1], array[i + 2]) == true){
            println("possible")
            return
        }
    }

    println("impossible")
}
