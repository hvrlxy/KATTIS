import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ").toList() // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

//this function only works for square matrix

var r = 0
var c = 0

val INF = 100000000

fun printMatrix(m: Array<IntArray>){
	for (i in 0 until r){
		println(m[i].joinToString(separator = " "))
	}
}

fun swap(m: Array<IntArray>, i: Int, j: Int): Array<IntArray>{
	// take a matrix m and swap row i and j
	var m = m

	val temp = IntArray(c){0}

	for (k in 0 until c){
		temp[k] = m[i][k]
		m[i][k] = m[j][k]
		m[j][k] = temp[k]
	}

	return m
}

fun scalar(m: Array<IntArray>, i: Int, s: Int): IntArray{
	// multiply row i with scalar s

	var result = IntArray(c){0}
	for (j in 0 until c){
		result[j] = m[i][j] * s
	}
	return result
}

fun reduction(m: Array<IntArray>, i: Int, j: Int, k: Int): Array<IntArray>{
	// turn m[j][k] into 0 by using row i
	//println()
	//printMatrix(m)
	//println("$i $j $k")

	if (m[j][k] == 0) return m
	var m = m

	// we need to find the scalar first (using lcm)

	val lcm = lcm(m[j][k], m[i][k])

	val scalarI = lcm / m[i][k]
	val scalarJ = lcm / m[j][k]

	val newI = scalar(m, i, scalarI)
	val newJ = scalar(m, j, scalarJ)

	for (p in 0 until c){
		m[j][p] = newI[p] - newJ[p]
	}

	//printMatrix(m)
	return m
}

fun gcd(a: Int, b: Int): Int{
	if (a == 0) return b else return gcd(b % a, a)
}

fun lcm(a: Int, b: Int): Int = a * b / gcd(a,b)

fun guass(m: Array<IntArray>): Array<IntArray>{
	// turn m into reduced echolon form
	var m = m

	// turn the matrix into an upper triangle matrix
	for (i in 0 until c){
		// we want to reduce variable i-th

		var j = i
		while (j < r - 1 && m[j][i] == 0){
			j ++
		}

		//println("$j $i ${r} ${m[0].size}")
		if (j >= r || m[j][i] == 0) continue
		if (i != j) m = swap(m, i, j)

		for (k in i + 1 until r){
			m = reduction(m, i, k, i)
		}
	}

	//turn the matrix into a diagonal matrix
	for (i in 0 until c){
		for (j in i + 1 until min(r,c)){

			if (m[j][j] != 0) m = reduction(m, j, i, j)
		}
	}

	for (i in 0 until min(r,c)){
		val gcd = gcd(m[i][i], m[i][c - 1])
		if (gcd != 0){
			m[i][i] /= gcd
			m[i][c - 1] /= gcd
		}
	}

	return m
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var aline = rd.readStrings()
    val aMap = mutableMapOf<String, Int>()

    var variable = 0
    var function = 0

    val aList = mutableListOf<List<String>>()
    while (aline[0].toInt() != 0 && aline[1].toInt() != 0){
    	variable ++

    	for (i in 2 until aline.size step 2){

    		if (aline[i] !in aMap.keys){
    			aMap[aline[i]] = function
    			function ++
    		}
    	}
    	aList.add(aline)
    	aline = rd.readStrings()
    }

    r = function
    c = variable

    val m = Array<IntArray>(r){IntArray(c){0}}

    for (l in 0 until aList.size){
    	for (i in 2 until aList[l].size step 2){
    		m[aMap[aList[l][i]]!!][l] += aList[l][0].toInt() * aList[l][i + 1].toInt()
    	}
    }

    for (i in 0 until r){
    	m[i][c - 1] *= -1
    }

    var reducedM = guass(m)

    var e = 1
    for (i in 0 until c - 1){
    	e = lcm (e, reducedM[i][i])
    }

    val result = IntArray(variable){0}
    result[variable - 1] = e

    for (i in 0 until c - 1){
    	result[i] = e * reducedM[i][c - 1] / reducedM[i][i]
    }

    println(result.joinToString(separator = " "))
}