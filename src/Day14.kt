import java.io.File


fun main() {
    val lines = File("src/Day14.txt").readLines()
    val polymerTemplate = lines.first()
    val pairInsertions = lines.drop(2).map {
        val (k, v) = it.split(" -> ")
        k to v
    }

    //solvePartOne(polymerTemplate, pairInsertions)

    solvePartTwo(polymerTemplate, pairInsertions)
}

private fun solvePartOne(polymerTemplate: String, pairInsertions: List<Pair<String, String>>) {
    var result = polymerTemplate
    repeat(10) {
        var intermediateResult = result.first().toString()
        for (i in 0..result.length - 2) {
            val a = result.substring(i, i + 2)
            val b = pairInsertions.first { it.first[0] == a[0] && it.first[1] == a[1] }.second
            intermediateResult += "$b${a[1]}"
        }
        result = intermediateResult
    }
    println(result.groupBy { it }.maxOf { it.value.size } - result.groupBy { it }.minOf { it.value.size })
}

private fun solvePartTwo(polymerTemplate: String, pairInsertions: List<Pair<String, String>>) {
    var state = polymerTemplate.zip(polymerTemplate.drop(1)) { a, b -> "$a$b" }
        .groupingBy { it }.eachCount().mapValues { it.value.toLong() }
    repeat(40) {
        state = buildMap {
            for ((src, n) in state) {
                for (dst in pairInsertions.filter { it.first == src }) {
                    put(dst.second, getOrElse(dst.second) { 0 } + n)
                }
            }
        }
    }
    val counts = buildMap<Char, Long> {
        put(polymerTemplate.first(), 1)
        put(polymerTemplate.last(), getOrElse(polymerTemplate.last()) { 0 } + 1)
        for ((pair, n) in state) {
            for (c in pair) {
                put(c, getOrElse(c) { 0 } + n)
            }
        }
    }
    println(counts.values.maxOrNull()!! - counts.values.minOrNull()!!)
}
