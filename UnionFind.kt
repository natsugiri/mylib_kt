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
