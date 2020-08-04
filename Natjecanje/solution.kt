import java.util.Scanner
import java.io.*

fun main(){
	val sc = Scanner(System.`in`)

	val total = sc.nextInt()

	val damageList = MutableList<Int>(sc.nextInt()){0}
	val reserveList = MutableList<Int>(sc.nextInt()){0}

	for (i in 0 until damageList.size){
		damageList[i] = sc.nextInt()
	}

	for (i in 0 until reserveList.size){
		reserveList[i] = sc.nextInt()
	}

	damageList.sort()
	reserveList.sort()

	var i = 0
	while (i < damageList.size){
		if ((reserveList.size != 0) && (damageList.size != 0)){
			var j = 0
			val x = damageList[i]
			while (j < reserveList.size){
				if ((damageList[i] == reserveList[j] - 1) || 
				(damageList[i] == reserveList[j] + 1) ||
				 (damageList[i] == reserveList[j])){
					damageList.removeAt(i)
					reserveList.removeAt(j)
					break
				}
				else{
					j++
				}
			}

			if (x in damageList){
				i ++
			}
		}
		else{
			break
		}
	}

	println(damageList.size)
}