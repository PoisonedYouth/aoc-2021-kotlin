import java.io.File


fun main() {
    val lines = File("src/Day12.txt").readLines()

    val names = mutableMapOf<String, Int>()
    val edges = mutableMapOf<Int, MutableList<Pair<Int, Boolean>>>()
    for (line in lines) {
        val (lhs, rhs) = line.split('-', limit = 2)
        val src = names.getOrPut(lhs) { names.size }
        val dst = names.getOrPut(rhs) { names.size }
        edges.getOrPut(src) { mutableListOf() }.add(dst to rhs.all { it.isUpperCase() })
        edges.getOrPut(dst) { mutableListOf() }.add(src to lhs.all { it.isUpperCase() })
    }
    val start = names.getValue("start")
    val end = names.getValue("end")

    solvePartOne(edges, start, end)

    solvePartTwo(edges, start, end)
}

fun solvePartOne(edges: MutableMap<Int, MutableList<Pair<Int, Boolean>>>, start: Int, end: Int) {
    var count = 0
    walk(Int.MIN_VALUE to start) { (state, element) ->
        edges[element]?.mapNotNull { (next, big) ->
            when {
                next == start -> null
                next == end -> null.also { count++ }
                big -> state to next
                state.and(1 shl next) == 0 -> state.or(1 shl next) to next
                state >= 0 -> state or Int.MIN_VALUE to next
                else -> null
            }
        }.orEmpty()
    }
    println(count)
}

fun solvePartTwo(edges: MutableMap<Int, MutableList<Pair<Int, Boolean>>>, start: Int, end: Int) {
    var count = 0
    walk(0 to start) { (state, element) ->
        edges[element]?.mapNotNull { (next, big) ->
            when {
                next == start -> null
                next == end -> null.also { count++ }
                big -> state to next
                state.and(1 shl next) == 0 -> state.or(1 shl next) to next
                state >= 0 -> state or Int.MIN_VALUE to next
                else -> null
            }
        }.orEmpty()
    }
    println(count)
}


private fun <T> walk(start: T, step: (T) -> Iterable<T>) {
    val stack = mutableListOf(step(start).iterator())
    while (stack.isNotEmpty()) {
        val iterator = stack.last()
        if (!iterator.hasNext()) {
            stack.removeLast()
            continue
        }
        stack.add(step(iterator.next()).iterator())
    }
}

