import java.util.Scanner
fun main(){
	val sc = Scanner(System.`in`)
	while (sc.hasNext()){
		val N = sc.next().split("/")[1].toInt()
		//(X - N)(Y - N) = N^2
		var result = 0
		val Nsquare = N * N
		for (i in 1 .. N){
			if (Nsquare % i == 0) result ++
		}
	
		println(result)
	}
}