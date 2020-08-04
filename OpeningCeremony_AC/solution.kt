fun main(){
	readLine()
	val buildings = readLine()!!.split(" ").map { it.toInt() }.toMutableList()
	buildings.sort()
	var widthSlices = 0
	var verticalSlices = 0
	while (buildings.isNotEmpty()){
		if (buildings.size > buildings[buildings.size - 1] - widthSlices){
			widthSlices ++
			while (buildings.isNotEmpty() && buildings[0] <= widthSlices){
				buildings.removeAt(0)
			}
		}
		else{
			buildings.removeAt(buildings.size - 1)
			verticalSlices ++
		}
	}
	println(verticalSlices + widthSlices)
}