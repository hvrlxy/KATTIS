import java.util.Scanner

fun main(){
	val sc = Scanner(System.`in`)

	val n = sc.nextInt()

	val a = (sc.next()!!.split(""))
	val b = (sc.next()!!.split(""))

	val c = a.toMutableList()
	val d = b.toMutableList()
	c.removeAt(0)
	c.removeAt(c.size - 1)
	d.removeAt(0)
	d.removeAt(d.size - 1)

	//println(c)
	//println(d)

	var r = 0
	var s = 0

	var i = 0
	while (i < c.size){
		if (c[i] == d[i]){
			//println("${c[i]} ${d[i]}")
			r ++
			c.removeAt(i)
			d.removeAt(i)
		}

		else{
			i ++
		}
	}

	//println(c)
	//println(d)

	for (i in 0 until c.size){
		for (j in 0 until d.size){
			if (c[i] == d[j]){
				s ++
				d.removeAt(j)
				break
			}
		}
	}

	println("$r $s")
}