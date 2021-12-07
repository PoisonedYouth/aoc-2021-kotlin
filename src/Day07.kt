import java.io.File
import kotlin.math.abs

fun main() {
    val lines = File("src/Day07.txt").readText().split(",").map { it.toInt() }.toMutableList()

    solvePartOne(lines)

    solvePartTwo(lines)
}

fun solvePartOne(lines: MutableList<Int>) {
    val median = med(lines)
    println(lines.sumOf { abs(it - median) })
}

fun solvePartTwo(lines: MutableList<Int>) {
    val min = lines.minOf { it }
    val max = lines.maxOf { it }

    val costs = mutableListOf<Int>()
    for (i in min..max) {
        costs.add(lines.sumOf { (1..(abs(it - i))).sum() })
    }
    println(costs.minOf { it })
}

fun med(list: List<Int>) = list.sorted().let {
    if (it.size % 2 == 0) (it[it.size / 2] + it[(it.size - 1) / 2]) / 2
    else it[it.size / 2]
}

