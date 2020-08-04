import java.util.Scanner
import kotlin.math.pow

val MODBY = 1000000007L

fun main(){
    val sc = Scanner(System.`in`)

    val table = LongArray(10001){0}

    table[1] = 2L
    table[2] = 3L

    for (i in 3 until table.size){
        table[i] = (table[i - 1] + table[i - 2]) % MODBY
    }

    //println(table.joinToString())

    val numCase = sc.nextInt()

    for (i in 0 until numCase){
        val n = sc.nextInt()

        println(table[n])
    }
}
