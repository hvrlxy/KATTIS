fun main(args: Array<String>) {
     val s = args[0].toInt()
     val n = args[1].toInt()

    fun isSum(i: Int): Boolean{
        var i = i 
        var sum = 0
        while (i > 0){
            sum += i%10
            i /= 10
        }
        return (sum == s)
    }

     var result = 0
     for (i in 0 .. n){
        if (isSum(i)) result ++
    }
    println(result)
}
