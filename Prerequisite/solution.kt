import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	var numCourses = sc.nextInt()
	var categories = sc.nextInt()
	while (numCourses + categories != 0){
		val courseArray = Array<String>(numCourses){sc.next()}

		var isValid = true
		repeat(categories){
			if (isValid){
				val courses = sc.nextInt()
				var requirements = sc.nextInt()

				repeat(courses){
					if (sc.next() in courseArray) requirements --
				}
				if (requirements > 0) {
					println("no")
					isValid = false
				}
			}
			else{
				val courses = sc.nextInt()
				var requirements = sc.nextInt()

				repeat(courses){
					sc.next()
				}
			}
		}

		if (isValid) println("yes")

		numCourses = sc.nextInt()
		if (numCourses != 0) {
			categories = sc.nextInt() 
		}
		else return
	}
}