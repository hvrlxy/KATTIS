import java.util.Scanner
import java.io.*
import kotlin.math.*

fun main(){
    val sc = Scanner(BufferedReader(InputStreamReader(System.`in`)))

    val numLocations = sc.nextInt()
    val wArray = IntArray(numLocations + 1){0}

    for (i in 1 until numLocations + 1){
        wArray[i] = sc.nextInt()
    }

    //maxArray[1] = wArray[1]

    val roads = sc.nextInt()

    val adj = Array<IntArray>(numLocations + 1){IntArray(numLocations + 1){-1}}

    for (i in 0 until roads){
        val a = sc.nextInt()
        val b = sc.nextInt()
        val w = sc.nextInt()
        adj[a][b] = w
        adj[b][a] = w
    }

    val d = IntArray(numLocations + 1){Int.MAX_VALUE}

    d[1] = 0

    val S = mutableListOf<Int>()
    val p = IntArray(numLocations + 1){0}

    val Sbar = mutableListOf<Int>()
    for (i in 1 .. numLocations){
        if (d[i] < Int.MAX_VALUE && i !in S){
            Sbar.add(i)
        }
    }

    while (Sbar.isNotEmpty()){
        var v = -1
        var min = Int.MAX_VALUE
        for (i in Sbar){
            if (d[i] < min){
                min = d[i]
                v = i
            }
        }

        S.add(v)
        Sbar.remove(v)

        for (w in 1 .. numLocations){
            if (adj[v][w] != -1 && w !in S && d[v] + adj[v][w] < d[w]){
                d[w] = d[v] + adj[v][w]
                p[w] = v
            }
        }

        for (i in 1 .. numLocations){
            if (d[i] < Int.MAX_VALUE && i !in S && i !in Sbar){
                Sbar.add(i)
            }
        }
    }

    if (d[numLocations] == Int.MAX_VALUE){
        println("impossible")
        return
    }

    val m = IntArray(numLocations + 1){0}
    m[1] = wArray[1]

    for (v in S){
    	for (u in 0 .. numLocations){
    		if (adj[v][u] != -1){
    			if (d[v] == d[u] + adj[v][u] && m[v] < m[u] + wArray[v]){
    				m[v] = m[u] + wArray[v]
    			}
    		}
    	}
    }

    println("${d[numLocations]} ${m[numLocations]}")
}







