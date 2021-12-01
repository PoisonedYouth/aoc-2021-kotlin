import java.io.File

fun main() {
    val lines = File("src/Day01.txt").readLines().map { it.toInt() }

    solvePartOne(lines)

    solvePartTwo(lines)
}

private fun solvePartOne(lines: List<Int>) {
    println(lines.windowed(2).count { it[1] > it[0] })
}

private fun solvePartTwo(lines: List<Int>) {
    println(lines.windowed(3). map { it.sum() }.windowed(2).count { it[1] > it[0] })
}
