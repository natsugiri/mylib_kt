// returns index;
fun IntArray.lowerBound(key: Int): Int {
    var lo = -1
    var hi = this.size
    while (hi - lo > 1) {
        val mid = (lo + hi) / 2
        if (this[mid] < key) lo = mid
        else hi = mid
    }
    return hi
}

fun LongArray.lowerBound(key: Long): Int {
    var lo = -1
    var hi = this.size
    while (hi - lo > 1) {
        val mid = (lo + hi) / 2
        if (this[mid] < key) lo = mid
        else hi = mid
    }
    return hi
}

fun IntArray.createUnique(): IntArray {
    val a = sortedArray()
    var len = 0
    repeat(size) {
        if (len == 0 || a[len - 1] != a[it]) {
            a[len] = a[it]
            len++
        }
    }
    return IntArray(len) { a[it] }
}
