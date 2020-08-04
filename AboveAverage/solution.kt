import java.util.Scanner
import java.io.*

fun main() {
    val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

    val numCase = sc.nextInt()
    for (i in 0 until numCase){
        val numPeople = sc.nextInt()
        val array = IntArray(numPeople){sc.nextInt()}

        val average = array.sum().toDouble() / numPeople
        var total = 0
        for (i in 0 until numPeople){
            if (array[i] > average){
                total ++
            }
        }
        println("%.3f%s".format(total.toFloat() * 100.0/numPeople, "%"))
    }
}
