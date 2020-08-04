import java.util.Scanner
import java.io.*

fun main(){
    val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

    val table = IntArray(10001){0}

    val primeSet = mutableSetOf<Int>()
    val bArray = java.util.BitSet(10001)
    bArray.flip(2, bArray.size() - 1)

    for (i in 2 .. 10001){
        if (bArray[i]){
            primeSet.add(i)
            var j = 2
            while (i * j <= 10000){
                bArray.clear(i * j)
                j ++
            }
        }
    }

    fun findPhi(n: Int): Int{
        var p = 1L
        var q = 1L
        for (i in primeSet){
            if (n % i == 0){
                p *= (i - 1)
                q *= i
            }
        }
        return (n.toLong() * p.toLong() / q.toLong()).toInt()
    }

    table[1] = 2

    for (i in 2 until table.size){
        table[i] = table[i - 1] + findPhi(i)
    }
    
    val numCase = sc.nextInt()

    for (i in 0 until numCase){
        val caseNo = sc.nextInt()

        println("$caseNo ${table[sc.nextInt()]}")
    }
}

