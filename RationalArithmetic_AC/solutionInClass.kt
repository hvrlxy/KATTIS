import java.util.Scanner

fun main() {
     val sc = Scanner(System.`in`)

     val numCase = sc.nextInt()

     for (i in 0 until numCase){
        val x = Rational(sc.nextInt(), sc.nextInt())
        val operation = sc.next()
        val y = Rational(sc.nextInt(), sc.nextInt())

        if (operation == "+"){
            println("${(x + y)}")
        }

        else if (operation == "-"){
            println("${(x - y)}")
        }

        else if (operation == "*"){
            println("${(x * y)}")
        }

        else{
            println("${(x / y)}")
        }
    }
}

class Rational(var p: Int, var q: Int){
    fun gcd(p: Int, q: Int): Int{
        var newX = p
        var newY = q

        if (p < 0){
            newX *= -1
        }

        if (q < 0){
            newY *= -1
        }

        while (newY > 0){
            val z = newX % newY
            newX = newY
            newY = z
        }

        return newX
    }

    operator fun plus(r: Rational): Rational{
        var gcd = gcd(p * r.q + r.p * q, q * r.q)
        var numerator = (p * r.q + r.p * q) / gcd
        var denominator = (q * r.q) / gcd

        if (numerator < 0 && denominator < 0){
            numerator *= -1
            denominator *= -1
        }

        if (numerator > 0 && denominator < 0){
            numerator *= -1
            denominator *= -1
        }

        return(Rational(numerator, denominator))
    }

    operator fun minus(r: Rational): Rational{
        var gcd = gcd(p * r.q - r.p * q, q * r.q)
        var numerator = (p * r.q - r.p * q) / gcd
        var denominator = (q * r.q) / gcd

        if (numerator < 0 && denominator < 0){
            numerator *= -1
            denominator *= -1
        }

        if (numerator > 0 && denominator < 0){
            numerator *= -1
            denominator *= -1
        }

        return(Rational(numerator, denominator))
    }

    operator fun times(r: Rational): Rational{
        var gcd = gcd(p * r.p , q * r.q)
        var numerator = (p * r.p) / gcd
        var denominator = (q * r.q) / gcd

        if (numerator < 0 && denominator < 0){
            numerator *= -1
            denominator *= -1
        }

        if (numerator > 0 && denominator < 0){
            numerator *= -1
            denominator *= -1
        }

        return (Rational(numerator, denominator))
    }

    operator fun div(r: Rational): Rational{
        var gcd = gcd(p * r.q , q * r.p)
        var numerator = (p * r.q) / gcd
        var denominator = (q * r.p) / gcd

        if (numerator < 0 && denominator < 0){
            numerator *= -1
            denominator *= -1
        }

        if (numerator > 0 && denominator < 0){
            numerator *= -1
            denominator *= -1
        }

        return(Rational(numerator, denominator))
    }

    override fun toString(): String{
        return ("$p / $q")
    }
}
