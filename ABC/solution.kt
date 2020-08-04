import java.util.Scanner
fun main() {
     val sc = Scanner(System.`in`)

     var array = IntArray(3){sc.nextInt()}
     array.sort()
     var aString = sc.next()
     if (aString == "ABC") println("${array[0]} ${array[1]} ${array[2]}")
     else if (aString == "ACB") println("${array[0]} ${array[2]} ${array[1]}")
     else if (aString == "BCA") println("${array[1]} ${array[2]} ${array[0]}")
     else if (aString == "BAC") println("${array[1]} ${array[0]} ${array[2]}")
     else if (aString == "CAB") println("${array[2]} ${array[0]} ${array[1]}")
     else println("${array[2]} ${array[1]} ${array[0]}")
}
