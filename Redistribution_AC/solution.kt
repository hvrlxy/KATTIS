import java.io.*
import kotlin.math.*
import java.util.PriorityQueue

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

val INF = 100000000

data class Distance (val i: Int, val j: Int, val d: Int): Comparable<Distance>{
	//i is the index of the judge
	override fun compareTo(other: Distance): Int {
		if (this.d.compareTo(other.d) != 0) return this.d.compareTo(other.d)
		else if (this.i.compareTo(other.i) != 0) return this.i.compareTo(other.i)
		else{
			return this.j.compareTo(other.j)
		}
	}
}

data class Point (val x: Int, val y: Int)

fun squareDist (a: Point, b: Point): Int = (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y)

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val judges = s[0]
    val tar = s[1]
    val feather = s[2]

    var judgeList = mutableListOf<Point>()
    //read in the judges
    repeat(judges){
    	val a = rd.readInts()
    	judgeList.add(Point(a[0], a[1]))
    }

    var total = 0.0

    //deal with the tar first
    var Q = PriorityQueue<Distance>()
    var tarList = mutableListOf<Point>()
    repeat(tar){
    	val a = rd.readInts()
    	tarList.add(Point(a[0], a[1]))
    }

    val isTar = BooleanArray(judges)
    val isTaken = BooleanArray(tar)

    for (i in 0 until judges){
    	for (j in 0 until tar){
    		Q.add(Distance(i, j, squareDist(judgeList[i], tarList[j])))
    	}
    }

    var numJudges = 0

    while (numJudges < judges){
    	val d = Q.poll()

    	if (isTar[d.i] || 
    		isTaken[d.j]) continue

    	isTar[d.i] = true
    	isTaken[d.j] = true

    	total += sqrt(d.d.toDouble())
    	numJudges ++
    }

	//deal with the feather next
	Q = PriorityQueue<Distance>()
	val featherList = mutableListOf<Point>()

	repeat(feather){
		val a = rd.readInts()
		featherList.add(Point(a[0], a[1]))
	}

	for (i in 0 until judges){
		for (j in 0 until feather){
			Q.add(Distance(i, j, squareDist(judgeList[i], featherList[j])))
		}
	}

	val isFeather = BooleanArray(judges)
	val isTaken1 = BooleanArray(feather)

	numJudges = 0
	while (numJudges < judges){
		val d = Q.poll()

		if (isFeather[d.i] || isTaken1[d.j]) continue

		isFeather[d.i] = true
		isTaken1[d.j] = true

		total += sqrt(d.d.toDouble())
		numJudges ++
	}

	println(total)
}