import java.util.Scanner
import java.io.*

var oldTime = 0.0
var newTime = 0.0
var distance = 0.0
var speed = 0.0

fun main(){
    val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

    while (sc.hasNext()){
        val t = sc.next()

        var array = t.split(":")
        newTime = array[0].toDouble() * 1F + array[1].toDouble() * 1F/60F + array[2].toDouble() * 1F/3600F

        if (sc.hasNextInt()){
            distance += speed * (newTime - oldTime)
            speed = sc.nextInt().toDouble()
            //println(distance)
            oldTime = newTime
        }

        else{
            distance += speed * (newTime - oldTime)
            oldTime = newTime
            println("%s %.2f km".format(t, distance))
        }
    }
}

            
