import java.util.Scanner

fun main() {
     val sc = Scanner(System.`in`)
     val numPoints = sc.nextInt()
     println(numPoints)
     repeat(numPoints){
        println("${sc.nextInt() + 10000} ${sc.nextInt() + 10000}")
    }
}
