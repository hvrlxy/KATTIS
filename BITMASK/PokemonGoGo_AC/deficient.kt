fun main(){
    fun deficient(m: Int, n: Int): Int{
        if (n > m) return 0
        if (m == 1) return 1
        if (n == 1) return m
        return deficient(m, n - 1) + deficient(m - 1, n - 1)
    }
    println(deficient(4,4))
}
