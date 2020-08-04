fun main(){
	val array = readLine()!!.split(" ")
	val N = array[0].toLong()
	val K = array[1].toLong()

	val times = ((N - 1) / K)
	if (K >= N - 1) return println(N - 1)
	if (N - 1 == K) return println(N - 1)
	if ((N - 1) % K == 0L) return println(K + 1)
	if (times <= 1) return println(N - 1L)
	else return println(K + 1 + (N - 1) - times * K)
}