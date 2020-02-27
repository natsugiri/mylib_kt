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
        ret += a[r - 1]
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
            if (i + s <= n && a[i + s - 1] < key) {
                key -= a[i + s - 1]
                i += s
            }
            s /= 2
        }
        return i
    }
}
