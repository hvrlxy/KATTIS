fun main(){
    val array = mutableListOf<Long>()

    val maxLimit = 1000000000L

    var count = 1L

    while (count <= maxLimit){
        array.add(count)
        count *= 3L
    }

    println(array)
}
