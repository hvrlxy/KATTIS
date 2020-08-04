import java.util.Scanner
import java.io.*

fun main(){
    val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

    val numCase = sc.nextInt()
    for (i in 0 until numCase){
        val numPpl = sc.nextInt()
        val array = IntArray(3*numPpl){0}
        for (j in 0 until numPpl*3){
            array[j] = sc.nextInt()
        }
        array.sort()

        var count = 1
        var k = array.size - 2

        var result = 0

        while (k >= 0 && count <= numPpl){
            result += array[k]
            //println (array[k])
            k -= 2
            count ++
        }

        println(result)
    }
}
