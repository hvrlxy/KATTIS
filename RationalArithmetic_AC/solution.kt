import java.util.Scanner

fun main(){
    val sc = Scanner(System.`in`)

    val numCase = sc.nextInt()

    for (i in 0 until numCase){
        val x1 = sc.nextLong()
        val y1 = sc.nextLong()

        val operation = sc.next()

        val x2 = sc.nextLong()
        val y2 = sc.nextLong()

        var p = 0L
        var q = 0L

        if (operation == "+"){
            p = y2 * x1 + y1 * x2
            q = y1 * y2
        }
        else if (operation == "-"){
            p = x1 * y2 - x2 * y1
            q = y1 * y2
        }
        else if (operation == "*"){
            p = x1 * x2
            q = y1 * y2
        }
        else if (operation == "/"){
            p = x1 * y2
            q = x2 * y1
        }

        val a = gcd(p,q)

        p = p / a
        q = q / a

        //println(q)

        if ((p < 0L) && (q < 0L)){
            p *= -1L
            q *= -1L
        }

        if ((p > 0L) && (q < 0L)){
            p *= -1L
            q *= -1L
        }

        println("$p / $q")
    }
}

fun gcd(p: Long, q: Long): Long{
    var newX = p
    var newY = q

    if (p < 0L){
        newX *= -1L
    }
    if (q < 0L){
        newY *= -1L
    }

    while (newY > 0L){
        val z = newX % newY
        newX = newY
        newY = z
    }

    return newX
}
