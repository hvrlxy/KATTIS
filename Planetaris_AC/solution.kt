import java.util.Scanner

fun main(){
    val rd = Scanner(System.`in`)

    val numBattles = rd.nextInt()
    var shipHave = rd.nextInt()

    val shipList = IntArray(numBattles){rd.nextInt()}
    shipList.sort()

    var result = 0L
    var i = 0
    while (i < numBattles){
    	if (shipHave - (shipList[i] + 1) >= 0){
    		result ++
    		shipHave -= (shipList[i] + 1)
    		i ++
    	}
    	else break
    }
    println(result)
}