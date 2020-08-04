import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

//this function only works for square matrix
// be very careful when dealing with non-square matrix. Use min(r,c) as a bound
// for every m[i][i]

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

	return m
}

fun gcd(a: Int, b: Int): Int{
	if (a == 0) return b else return gcd(b % a, a)
}

fun lcm(a: Int, b: Int): Int = a * b / gcd(a,b)

fun guass(m: Array<IntArray>): DoubleArray{
	// turn m into reduced echolon form
	var m = m

	// turn the matrix into an upper triangle matrix
	for (i in 0 until r){
		// we want to reduce variable i-th

		var j = i
		while (m[j][i] == 0){
			j ++
		}
		if (i != j) m = swap(m, i, j)

		for (k in i + 1 until r){
			m = reduction(m, i, k, i)
		}
	}

	//turn the matrix into a diagonal matrix
	for (i in 0 until r){
		for (j in i + 1 until r){
			m = reduction(m, j, i, j)
		}
	}

	printMatrix(m)

	var result = DoubleArray(r){0.0}
	for (i in 0 until r){
		result[i] = m[i][c - 1] / m[i][i].toDouble()
	}
	return result
}

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    r = s[0]
    c = s[1]

    // read in the matrix

    val m = Array<IntArray>(r){IntArray(c){0}}

    for (i in 0 until r){
    	val a = rd.readInts()

    	for (j in 0 until c){
    		m[i][j] = a[j]
    	}
    }
    guass(m)

    //println(guass(m).joinToString(separator = " "))
}