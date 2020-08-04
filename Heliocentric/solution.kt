import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	var caseNo = 1
	while (sc.hasNext()){
		var earth = sc.nextInt()
		var mars = sc.nextInt()

		var result = 0

		while (earth % 365 != 0 || mars % 687 != 0){
			earth ++
			mars ++
			result ++
		}

		println("Case $caseNo: $result")
		caseNo ++
	}
}