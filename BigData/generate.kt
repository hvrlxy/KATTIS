import kotlin.math.*

fun main(){
	val bArray = java.util.BitSet(1000+1)

    var numPrime = mutableListOf<Int>()
    bArray.flip(2, 1000 + 1)

    val sq = sqrt(1000.toDouble()).roundToInt()
    for (i in 2 .. sq){
        if (bArray[i]){
            var j = i
            while (i * j <= 1000){
                bArray.clear(i * j)
                j ++
            }
        }
    }

    for (i in 2 .. bArray.size()){
    	if (bArray[i]) numPrime.add(i)
    }
    println(numPrime)
}