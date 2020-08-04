import kotlin.math.*

data class Interval (val s: Int, val e: Int, val i: Int)

fun main(){
	var aline = readLine()
	val printResult = StringBuilder()
	while (aline != null){
		val I = aline.split(" ").map{(it.toDouble() * 1000000).toInt()}

		val numIntervals = readLine()!!.toInt()
		var intervalList = mutableListOf<Interval>()
		var count = 0
		repeat(numIntervals){
			val aline = readLine()!!.split(" ").map{(it.toDouble() * 1000000).toInt()}

			intervalList.add(Interval(aline[0], aline[1], count))
			count++
		}

		intervalList.sortBy{it.s}

		val alternativeList = mutableListOf<Interval>()

		for (i in intervalList){
			if (i.s > I[1] || i.e < I[0]) continue else alternativeList.add(i)
		}

		val dp = Array<MutableList<Int>>(alternativeList.size){mutableListOf<Int>()}
		//println(alternativeList.joinToString(separator = ","))

		//Let dp[i] be the minimal set that cover interval (I[0] .. dp[i].e)
		var max = Int.MAX_VALUE
		var result = mutableListOf<Int>()

		for (i in 0 until alternativeList.size){
			if (alternativeList[i].s <= I[0]) dp[i] = mutableListOf(alternativeList[i].i)
			else{
				for (j in 0 until i){
					if (alternativeList[j].e >= alternativeList[i].s){
						if (dp[i].isEmpty() || (dp[j].size + 1 < dp[i].size && !dp[j].isEmpty())) {
							dp[i].addAll(dp[j])
							dp[i].add(alternativeList[i].i)
						}
					}
				}
			}

			if (!dp[i].isEmpty() && alternativeList[i].e >= I[1] && dp[i].size < max){
				max = dp[i].size
				result = dp[i]
			}
		}

		//println(dp.joinToString(separator = ","))

		if (max == Int.MAX_VALUE) printResult.append("impossible\n")
		else{
			printResult.append("$max\n")
			printResult.append(result.joinToString(separator = " "))
			printResult.append("\n")
		}

		aline = readLine()
	}
	print(printResult)
}