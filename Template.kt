import java.util.*

fun main(args: Array<String>) = with(Scanner(System.`in`)) {
    val n = nextInt()
	val a = IntArray(n) { nextInt() }
	a.sort()
    println(a.joinToString(" "))
}