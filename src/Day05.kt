import java.io.File

fun main() {
    val lines = File("src/Day05.txt").readLines()

    solvePartOne(lines)

    solvePartTwo(lines)
}

fun solvePartOne(lines: List<String>) {
    val board = Array(1000) { Array(1000) { 0 } }
    for (line in lines) {
        val startEnd = line.split("->")
        var (x0, y0) = startEnd[0].trim().split(",").map { it.toInt() }
        val (x1, y1) = startEnd[1].trim().split(",").map { it.toInt() }

        // Not recognize diagonal
        if (x0 != x1 && y0 != y1) continue

        board[x0][y0]++
        while (x0 != x1 || y0 != y1) {
            if (x0 != x1) {
                if (x0 > x1) x0--
                else x0++
            } else {
                if (y0 > y1) y0--
                else y0++
            }
            board[x0][y0]++
        }
    }
    println(board.sumOf { row -> row.count { value -> value >= 2 } })
}


fun solvePartTwo(lines: List<String>) {
    val board = Array(1000) { Array(1000) { 0 } }
    for (line in lines) {
        val startEnd = line.split("->")
        var (x0, y0) = startEnd[0].trim().split(",").map { it.toInt() }
        val (x1, y1) = startEnd[1].trim().split(",").map { it.toInt() }

        board[x0][y0]++
        while (x0 != x1 || y0 != y1) {
            if (x0 != x1) {
                if (x0 > x1) x0--
                else x0++
            }
            if (y0 != y1) {
                if (y0 > y1) y0--
                else y0++
            }
            board[x0][y0]++
        }
    }
    println(board.sumOf { row -> row.count { value -> value >= 2 } })
}
