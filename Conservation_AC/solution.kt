import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numCase = rd.readInt()
    var printResult = StringBuilder()
    repeat(numCase){
        val a = rd.readInts()

        val labNum = rd.readInts()

        var adj = Array<MutableList<Int>>(a[0]){mutableListOf<Int>()}

        var indegreeArray = IntArray(a[0]){0}
        var indegreeArray1 = IntArray(a[0]){0}
        repeat(a[1]){
            val dependency = rd.readInts()
            adj[dependency[0] - 1].add(dependency[1] - 1)
            indegreeArray[dependency[1] - 1] ++
            indegreeArray1[dependency[1] - 1] ++
        }

        val Q1 = mutableListOf<Int>()
        val Q2 = mutableListOf<Int>()
        val Q3 = mutableListOf<Int>()
        val Q4 = mutableListOf<Int>()

        // start the topological sorting in the first factory

        for (i in 0 until indegreeArray.size){
            if (indegreeArray[i] == 0 && labNum[i] == 1){
                //println(i)
                Q1.add(i)
                Q3.add(i)
            }
            else if (indegreeArray[i] == 0 && labNum[i] == 2){
                //println(i)
                Q2.add(i)
                Q4.add(i)
            }
        }

        var currentLab = 1
        var result1 = 0

        while (Q1.isNotEmpty() || Q2.isNotEmpty()){
            if (currentLab == 1){
                while (Q1.isNotEmpty()){
                    val v = Q1[Q1.size - 1]
                    Q1.removeAt(Q1.size - 1)
                    for (w in adj[v]){
                        indegreeArray[w] --
                        if (indegreeArray[w] == 0 && labNum[w] == 1) Q1.add(w)
                        else if (indegreeArray[w] == 0 && labNum[w] == 2) Q2.add(w)
                    }
                }
            }
            else if (currentLab == 2){
                while (Q2.isNotEmpty()){
                    val v = Q2[Q2.size - 1]
                    Q2.removeAt(Q2.size - 1)
                    for (w in adj[v]){
                        indegreeArray[w] --
                        if (indegreeArray[w] == 0 && labNum[w] == 1) Q1.add(w)
                        else if (indegreeArray[w] == 0 && labNum[w] == 2) Q2.add(w)
                    }
                }
            }
            currentLab = 3 - currentLab
            result1++
        }

        currentLab = 2
        var result2 = 0
        while (Q3.isNotEmpty() || Q4.isNotEmpty()){
            if (currentLab == 1){
                while (Q3.isNotEmpty()){
                    val v = Q3[Q3.size - 1]
                    Q3.removeAt(Q3.size - 1)
                    for (w in adj[v]){
                        indegreeArray1[w] --
                        if (indegreeArray1[w] == 0 && labNum[w] == 1) Q3.add(w)
                        else if (indegreeArray1[w] == 0 && labNum[w] == 2) Q4.add(w)
                    }
                }
            }

            else if (currentLab == 2){
                while (Q4.isNotEmpty()){
                    val v = Q4[Q4.size - 1]
                    Q4.removeAt(Q4.size - 1)
                    for (w in adj[v]){
                        indegreeArray1[w] --
                        if (indegreeArray1[w] == 0 && labNum[w] == 1) Q3.add(w)
                        else if (indegreeArray1[w] == 0 && labNum[w] == 2) Q4.add(w)
                    }
                }
            }
            currentLab = 3 - currentLab
            result2++
        }

        result1 --
        result2 --
        if (result1 > result2) printResult.append("$result2\n") else printResult.append("$result1\n")
    }
    print(printResult)
}