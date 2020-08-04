import java.util.Scanner
import java.io.*

fun main() {
    val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

    val numPeople = sc.nextInt()
    val numOpen = sc.nextInt()
    val array = Array<Person>(numPeople){Person(sc.nextInt(), sc.nextInt())}
    
    array.sortBy{it.time}
    array.sortByDescending{it.money}

    val timeArray = IntArray(numOpen){0}
    
    for (i in 0 until array.size){
        for (j in array[i].time downTo 0){
            if (array[i].time < numOpen && timeArray[j] == 0){
                timeArray[j] = array[i].money
                break
            }
        }
    }

    println(timeArray.sum())
}

data class Person(var money: Int, var time: Int)
