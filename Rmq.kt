class Rmq(val n: Int) {
    val a = LongArray(n*2) { Long.MAX_VALUE }

    constructor(n: Int, init: (Int) -> Long) : this(n) {
        for (i in 0..n - 1) a[i + n] = init(i)
        for (i in n - 1 downTo 1) a[i] = minOf(a[i*2], a[i*2 + 1])
    }

    fun at(i: Int): Long {
        return a[i + n]
    }

    fun modify(i_: Int, v: Long) {
        var i = i_ + n
        a[i] = v
        while (i > 1) {
            i /= 2
            a[i] = minOf(a[i*2], a[i*2 + 1])
        }
    }

    fun min(l_: Int, r_: Int): Long {
        var l = l_ + n
        var r = r_ + n
        var ret = Long.MAX_VALUE
        while (l < r) {
            if ((l and 1) == 1) { ret = minOf(ret, a[l]); l++; }
            if ((r and 1) == 1) { r--; ret = minOf(ret, a[r]); }
            l /= 2
            r /= 2
        }
        return ret
    }
}