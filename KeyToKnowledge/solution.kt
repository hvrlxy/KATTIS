import java.io.*
import kotlin.math.*
import java.util.LinkedList
import java.util.Queue
import java.util.PriorityQueue

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()

	repeat(numCase){
		val s = rd.readInts()
		val n = s[0]
		val m = s[1]

		data class Student(val s: String, val score: Int)

		val studentList = mutableListOf<Student>()

		var minScore = -1
		var minStudent  = -1

		for (i in 0 until n){
			val s = rd.readStrings()
			studentList.add(Student(s[0], s[1].toInt()))
			if (s[1].toInt() > minScore){
				minScore = s[1].toInt()
				minStudent = i
			}
		}

		var solution = 0
		var result = ""

		fun flip(s: String, i: Int): String{
			if (s[i] == '0') return s.replaceRange(i..i, "1")
			else return s.replaceRange(i..i, "0")
		}

		fun validStudent(c: String, s: Student): Boolean{
			var same = 0
			for (i in 0 until m){
				if (c[i] == s.s[i]) same ++
			}
			if (same == s.score) return true else return false
		}

		fun isValid(str: String): Boolean{
			for (s in studentList){
				if (!validStudent(str, s)) return false
			}
			return true
		}

		fun generateBinary(currentString: String, currentScore: Int, currentIdx: Int){
			//println("$currentString, $currentScore, $currentIdx")
			if (currentScore == minScore && isValid(currentString)){
				solution ++
				result = currentString
				return
			}

			if (currentIdx == m || currentScore < minScore) return

			generateBinary(currentString, currentScore, currentIdx + 1)
			generateBinary(flip(currentString, currentIdx), currentScore - 1, currentIdx + 1)
		}

		generateBinary(studentList[minStudent].s, m, 0)

		if (solution == 1) println(result)
		else println("$solution solutions")
	}
}