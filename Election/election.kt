import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

	val numCandidates = sc.nextLine().toInt()

	val aMap1 = mutableMapOf<String, String>() //map 1 keeps all the names of the candidates and their parties
	val aMap2 = mutableMapOf<String, Int>() // map 2 contains the names of the candidates and their ballots

	val candidatesArray = Array<String>(numCandidates){""}

	for (i in 0 until numCandidates){
		val name = sc.nextLine() // read in the name of the candidate
		//println(name)
		aMap1[name] = sc.nextLine() //key is the name of the candidate, value is the name of the party
		aMap2[name] = 0 // key is the name of the candidate, value is the number of votes
		candidatesArray[i] = name
	}
	//println(aMap1)
	//println(aMap2)
	//println(candidatesArray.joinToString())

	val numBallot = sc.nextLine().toInt()
	for (i in 0 until numBallot){
		val name = sc.nextLine()
		var current : Int = aMap2[name]!!
		current ++ //increment the number of votes
		aMap2[name] = current
	}

	val a = aMap2.values.toMutableList()
	a.sort()

	if ((a.size > 0) && (a[0] == a[1])){
		println("tie")
		return
	}

	var maxBallot = Int.MIN_VALUE
	var winner = ""

	for (i in 0 until numCandidates){
		//println(candidatesArray[i])
		if (aMap2[candidatesArray[i]]!! > maxBallot){
			winner = candidatesArray[i]
			maxBallot = aMap2[candidatesArray[i]]!!
		}
	}

	println(aMap1[winner]!!)
}