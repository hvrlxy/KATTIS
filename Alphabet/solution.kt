import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(System.`in`)

	val aString = sc.next()
	val alphabet = "abcdefghijklmnopqrstuvwxyz"

	val lcs = dpLcs(aString, alphabet)

	println(26 - lcs.length)
}


fun dpLcs(x: String, y: String): String {
    val m = x.length
    val n = y.length

    /* Creates a dp table `opt` with all entries set to 0. */
    val opt = Array<IntArray>(m+1) { IntArray(n+1) }

    /* Fills the table with values defined by the recurrence. */
    fun fillTable() {
        for (i in m-1 downTo 0) {
            for (j in n-1 downTo 0) {
                if (x[i] == y[j])
                    opt[i][j] = opt[i+1][j+1] + 1
                else
                    opt[i][j] = kotlin.math.max(opt[i][j+1], opt[i+1][j])
            }
        }
    }

    fun lcs(): String {
        val sb = StringBuilder()
        var i = 0
        var j = 0
        while ((i < m) && (j < n)) {
            when {
                x[i] == y[j] -> {
                    sb.append(x[i])
                    i++
                    j++
                }
                opt[i+1][j] > opt[i][j+1] -> i++
                else /* opt[i+1][j] <= opt[i][j+1] */ -> j++
            }
        }
        return sb.toString()
    }

    fillTable()
    return(lcs())
}