import java.util.Scanner
import java.io.*

fun main() {
    val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

    val numCase = sc.nextInt()

    for (l in 0 until numCase){
        val n = sc.nextInt()
        val m = sc.nextInt()
        val array = LongArray(n){0}
        val idx = mutableListOf<Int>()
        
        var maxSum = 0L
        
        for (i in 0 until n){
            array[i] = sc.nextLong()
            //println("${array[i]} $m")
            //add indices for m
            if (array[i] == m.toLong()){
                idx.add(i)
            }
        }
        
        //println(idx)

        for (i in 0 until idx.size){
            val indexM = idx[i]
            var sum = m.toLong()
            
            var j = indexM + 1
            //var j = indexM
            //println(array[j])
            //add the right size
            while(j < n && array[j] > m){
                //println(array[j])
                sum += array[j]
                j ++
            }
            
            //println(sum)
            
            var k = indexM - 1
            //println(array[k])
            //add the left size
            while(k >= 0 && array[k] > m){
                //println(array[k])
                sum += array[k]
                k --
            }
            

            //println(sum)
            if(sum > maxSum){
                maxSum = sum
            }
        }

        println(maxSum)
    }
}
