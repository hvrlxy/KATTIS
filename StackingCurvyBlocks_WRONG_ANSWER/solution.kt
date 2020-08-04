import java.io.*

private fun BufferedReader.readLn() = readLine()

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    var aString = rd.readLn()
    while (aString != null){
    	val b = aString.split(" ").map{it.toDouble()}
    	val t = rd.readLn().split(" ").map{it.toDouble()}

    	//println(b)
    	//println(t)

    	var fitquality = Double.MIN_VALUE
    	var minDistance = Double.MAX_VALUE

    	var i = 0.0
    	while (i < 1.0){
    		val fit = (t[0] + t[1] * i + t[2] * i * i + t[3] * i * i * i) - (b[0] + b[1] * i + b[2] * i * i + b[3] * i * i * i)
    		if (fit > fitquality) {
    			fitquality = fit
    		}
    		if (fit < minDistance) minDistance = fit
    		i += 0.00001
    	}

    	if (minDistance > 0.0) println("${fitquality - minDistance}")
    	else{
    		fitquality = Double.MIN_VALUE
    		var j = 0.0
	    	while (j < 1.0){
	    		val fit = minDistance + (t[0] + t[1] * i + t[2] * i * i + t[3] * i * i * i) - (b[0] + b[1] * i + b[2] * i * i + b[3] * i * i * i)
	    		if (fit > fitquality) {
	    			fitquality = fit
	    		}
	    		j += 0.00001
	    	}
	    	println("${fitquality - minDistance}")
    	}
    	aString = rd.readLn()
    }
}