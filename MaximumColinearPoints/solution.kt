import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

data class Point (val x: Int, val y: Int)

fun gcd(a: Int, b: Int): Int{
	if (b == 0) return a
	return gcd(b, a%b)
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var n = rd.readInt()
    while (n != 0){
    	val pointList = mutableListOf<Point>()

    	repeat(n){
    		val a = rd.readInts()
    		pointList.add(Point(a[0], a[1]))
    	}

    	var max = 1

    	for (i in 0 until pointList.size){
    		val aMap = mutableMapOf<Pair<Int, Int>, Int>()
    		for (j in 0 until pointList.size){
    			if (i != j){
	    			if (pointList[i].x == pointList[j].x){
	    				if ((pointList[j].y - pointList[i].y to 0) !in aMap.keys) {
	    					aMap[pointList[j].y - pointList[i].y to 0] = 2
	    					if (max == 1) max = 2
	    				}
	    				else{
	    					val c = aMap[pointList[j].y - pointList[i].y to 0]!!
	    					aMap[pointList[j].y - pointList[i].y to 0] = c + 1
	    					if (c + 1 > max) max = c + 1
	    				}
	    			}
	    			else{
	    				var a = pointList[j].y - pointList[i].y
	    				var b = pointList[j].x - pointList[i].x
	    				val gcd = gcd(a,b)

	    				a = a / gcd
	    				b = b / gcd
	    				if ((a to b) !in aMap.keys) {
	    					aMap[a to b] = 2
	    					if (max == 1) max = 2
	    				}
	    				else{
	    					val c = aMap[a to b]!!
	    					aMap[a to b] = c + 1
	    					if (c + 1 > max) max = c + 1
	    				}
	    			}
	    		}
    		}
    		//println(aMap)
    	}

    	println(max)
    	n = rd.readInt()
    }
}