import java.io.File

fun main() {
    val lines = File("src/Day09.txt").readLines().map { line -> line.toList().map { it.digitToInt() } }

    solvePartOne(lines)

    solvePartTwo(lines)
}

private fun solvePartOne(lines: List<List<Int>>) {
    val lowPoints = getLowPoints(lines)
    println(lowPoints.values.sum())
}


private fun solvePartTwo(lines: List<List<Int>>) {
    val lowPoints = getLowPoints(lines)
    val basin = mutableListOf<Int>()
    for (lowPoint in lowPoints) {
        val candidates = mutableListOf(lowPoint.key)
        val visited = mutableListOf<Pair<Int, Int>>()
        while (candidates.isNotEmpty()) {
            val next = candidates.removeFirst()
            candidates += getAdjacents(
                lines, next.first, next.second
            ).filter { it.value < 9 && !visited.contains(it.key) && !candidates.contains(it.key) }.keys
            visited += next
        }
        basin.add(visited.size)
    }
    val largestBasins = basin.sorted().takeLast(3)
    println(largestBasins[0] * largestBasins[1] * largestBasins[2])
}


private fun getLowPoints(lines: List<List<Int>>): Map<Pair<Int, Int>, Int> {
    val lowPoints = mutableMapOf<Pair<Int, Int>, Int>()
    for (i in lines.indices) {
        for (j in 0 until lines[i].size) {
            val value = lines[i][j]
            val adjacents = getAdjacents(lines, i, j)
            if (adjacents.all { it.value > value }) {
                lowPoints[Pair(i, j)] = value + 1
            }
        }
    }
    return lowPoints
}

fun getAdjacents(lines: List<List<Int>>, i: Int, j: Int): Map<Pair<Int, Int>, Int> {
    val adjacent = mutableMapOf<Pair<Int, Int>, Int>()
    if (i > 0) {
        adjacent[Pair(i - 1, j)] = lines[i - 1][j]
    }
    if (i < lines.size - 1) {
        adjacent[Pair(i + 1, j)] = lines[i + 1][j]
    }
    if (j > 0) {
        adjacent[Pair(i, j - 1)] = lines[i][j - 1]
    }
    if (j < lines[i].size - 1) {
        adjacent[Pair(i, j + 1)] = lines[i][j + 1]
    }
    return adjacent
}




