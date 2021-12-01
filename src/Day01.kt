import java.io.File

fun main() {
    val lines = File("src/Day01.txt").readLines().map { it.toInt() }

    solvePartOne(lines)

    solvePartTwo(lines)
}

private fun solvePartTwo(lines: List<Int>) {
    val sums = lines.windowed(3).map { it.sum() }
    var counter = 0
    for (index in 0 until sums.lastIndex) {
        if (sums[index + 1] > sums[index]) {
            counter++
        }
    }
    println(counter)
}

private fun solvePartOne(lines: List<Int>) {
    var counter = 0
    for (index in 0 until lines.lastIndex) {
        if (lines[index + 1] > lines[index]) {
            counter++
        }
    }
    println(counter)
}
