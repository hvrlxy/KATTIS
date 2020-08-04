fun main() {
     val oneCube = mutableListOf<Int>()
     val twoCube = mutableListOf<Int>()

     for (i in 1 until 74){
        for (j in i + 1 until 74){
           val cubeSum = i*i*i + j*j*j
           if (cubeSum in oneCube) twoCube.add(cubeSum)
           else oneCube.add(cubeSum)
        }
    }
    twoCube.sort()
    println(twoCube)
}
