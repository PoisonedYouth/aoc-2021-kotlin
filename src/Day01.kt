import java.io.File
import java.util.*


fun main() {
    val lines = File("src/Day02.txt").readLines()

    solveProblemOne(lines)

    solveProblemTwo(lines)
}

private fun solveProblemTwo(lines: List<String>) {
    val position = arrayOf(0, 0, 0)

    lines.map { it.split(" ") }.forEach {
        when (Direction.createDirection(it[0])) {
            Direction.Forward -> {
                position[0] += it[1].toInt()
                position[1] += position[2] * it[1].toInt()
            }
            Direction.Down -> position[2] += it[1].toInt()
            Direction.Up -> position[2] -= it[1].toInt()
        }
    }
    println(position[0] * position[1])
}

private fun solveProblemOne(lines: List<String>) {
    val position = arrayOf(0, 0)
    lines.map { it.split(" ") }.forEach {
        when (Direction.createDirection(it[0])) {
            Direction.Forward -> position[0] += it[1].toInt()
            Direction.Down -> position[1] += it[1].toInt()
            Direction.Up -> position[1] -= it[1].toInt()
        }
    }
    println(position[0] * position[1])
}

enum class Direction {
    Forward, Up, Down;

    companion object {
        fun createDirection(value: String) =
            valueOf(value.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
    }
}
