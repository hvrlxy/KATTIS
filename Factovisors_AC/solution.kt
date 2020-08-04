import kotlin.math.*

//very very annoying
//be aware of factorial 0 and the maximum number in Int range.

fun main(){
    val MAX = 60000
    val bArray = java.util.BitSet(MAX+1)
    bArray.flip(2, MAX + 1)
    val primes = mutableListOf<Int>()

    for (i in 2 .. MAX){
        if (bArray[i]){
            primes.add(i)
            var j = 2
            while (i * j <= MAX){
                bArray.clear(i * j)
                j ++
            }
        }
    }

    val result = StringBuilder()

    var aline = readLine()
    while (aline != null){
        val s = aline.split(" ").map{it.toLong()}

        var n = s[0]
        var m = s[1]

        var isValid = true
        if (s[1] == 1L){ // this is an edge case!!!!
            result.append("${s[1]} divides ${s[0]}!\n")
            isValid = false
        }

        for (i in primes){
            if (i > sqrt(m.toDouble()).toInt() + 1) break
            var j = 0L
            while (m % i == 0L){
                j ++
                m /= i
            }

            var k = i.toLong()
            while (k <= n){
                j -= n/k
                k *= i
            }
            if (j > 0){
                result.append("${s[1]} does not divide ${s[0]}!\n")
                isValid = false
                break
            }
        }

        if (isValid){
            if (m > n || s[1] == 0L ){
                result.append("${s[1]} does not divide ${s[0]}!\n")
            }
            else{
                result.append("${s[1]} divides ${s[0]}!\n")
            }
        }
        
        aline = readLine()
    }
    print(result)
}






