// returns index;
fun IntArray.lowerBound(key: Int): Int {
    var lo = -1
    var hi = this.size
    while (hi - lo > 1) {
	val mid = (lo + hi) / 2
	if (this[mid] < key) lo = mid;
	else hi = mid;
    }
    return hi
}

fun LongArray.lowerBound(key: Long): Int {
    var lo = -1
    var hi = this.size
    while (hi - lo > 1) {
	val mid = (lo + hi) / 2
	if (this[mid] < key) lo = mid;
	else hi = mid;
    }
    return hi
}

class Fenwick(val n: Int) {
    val a = LongArray(n)

    fun add(i_: Int, v: Long) {
	var i = i_
	while (i < n) {
	    a[i] += v
	    i = i or (i + 1)
	}
    }

    fun sum(r_: Int): Long {
	var r = r_
	var ret = 0L
	while (r > 0) {
	    ret += a[r-1]
	    r = r and (r - 1)
	}
	return ret
    }

    fun sum(l: Int, r: Int): Long {
	return sum(r) - sum(l)
    }

    fun lowerBound(key_: Long): Int {
	var key = key_
	var i = 0
	var s = 1
	while (s < n) s += s
	while (s > 0) {
	    if (i+s <= n && a[i+s-1] < key) {
		key -= a[i+s-1]
		i += s
	    }
	    s /= 2
	}
	return i
    }
}

class Rmq(val n: Int) {
    val a = LongArray(n*2) { Long.MAX_VALUE }

    constructor(n: Int, init: (Int) -> Long): this(n) {
	for (i in 0..n-1) a[i+n] = init(i)
	for (i in n-1 downTo 1) a[i] = minOf(a[i*2], a[i*2+1])
    }

    fun at(i: Int): Long {
	return a[i+n]
    }

    fun modify(i_: Int, v: Long) {
	var i = i_ + n
	a[i] = v
	while (i > 1) {
	    i /= 2
	    a[i] = minOf(a[i*2], a[i*2+1])
	}
    }

    fun min(l_: Int, r_: Int): Long {
	var l = l_ + n
	var r = r_ + n
	var ret = Long.MAX_VALUE
	while (l < r) {
	    if ((l and 1) == 1) { ret = minOf(ret, a[l]); l++; }
	    if ((r and 1) == 1) { r--; ret = minOf(ret, a[r]); }
	}
	return ret
    }
}

class UnionFind(n: Int) {
    val a = IntArray(n) { -1 }

    fun root(i: Int): Int {
	if (a[i] < 0) return i
	a[i] = root(a[i])
	return a[i]
    }

    fun link(i: Int, j: Int) {
	val i0 = root(i)
	val j0 = root(j)
	if (a[i0] <= a[j0]) {
	    a[i0] += a[j0]
	    a[j0] = i0
	} else {
	    a[j0] += a[i0]
	    a[i0] = j0
	}
    }

    fun same(i: Int, j: Int): Boolean {
	return root(i) == root(j)
    }

    fun count_node(i: Int): Int {
	return -a[root(i)]
    }
}

fun main(argv: Array<String>) {
    println(Long.MAX_VALUE)
    val a = IntArray(10) { it*2 }
    println(a.joinToString(" "))
    println(a.lowerBound(3))
    println(a.lowerBound(4))
    println(a.lowerBound(5))

    val f = Fenwick(10)
    f.add(5, 8)
    println(f.sum(5))
    println(f.sum(6))
    println("$f")
    println("${10 + 20}")
}

