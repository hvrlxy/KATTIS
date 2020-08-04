fun main(){
    val array = IntArray(10001){0}
    fun gcd(i: Int, j: Int): Int{
        var x = i
        var y = j

        while (y > 0){
            val z = x % y
            x = y
            y = z
        }
        return x
    }

    fun numCoPrime(n: Int): Int{
        var count = 0
        for (i in 1 until n){
            if (gcd(i,n) == 1){
                count ++
            }
        }
        return count
    }

    for (i in 2 until array.size){
        array[i] = numCoPrime(i)
    }
    println(array.joinToString())
}

