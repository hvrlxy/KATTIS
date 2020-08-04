import java.util.Scanner
import java.io.*
import java.math.BigInteger

fun main() {
    val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

    val numHours = sc.nextInt()

    var bac = 1L.toBigInteger()

    for (i in 0 until numHours){
        bac = bac.times(2L.toBigInteger())
        bac = bac.minus((sc.next()).toBigInteger())
        //bac = bac.times(2L.toBigInteger())
        //println(bac)
        if (bac.compareTo("0".toBigInteger()) == -1){
            println("error")
            return
        }
    }

    println(bac.rem("1000000007".toBigInteger()))
}
