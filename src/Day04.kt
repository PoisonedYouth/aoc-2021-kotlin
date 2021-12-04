import java.io.File


fun main() {
    val lines = File("src/Day04.txt").readLines()

    val bingoNumbers = lines.first().split(",").map { it.toInt() }

    val boards = setupBoard(lines)

    solvePartOne(bingoNumbers, boards)

    solvePartTwo(bingoNumbers, boards)
}

private fun solvePartOne(bingoNumbers: List<Int>, boards: MutableList<Board>) {
    for (index in bingoNumbers.indices) {
        markMatchingNumbers(boards, bingoNumbers[index])

        val matchingBoards = boards.filter { isBingo(it) }
        if (matchingBoards.isNotEmpty()) {
            println(matchingBoards[0].list.sumOf { it -> it.sumOf { it ?: 0 } } * bingoNumbers[index])
            break
        }
    }
}

fun solvePartTwo(bingoNumbers: List<Int>, boards: MutableList<Board>) {
    var lastWiningBoard: Board? = null
    for (index in bingoNumbers.indices) {
        markMatchingNumbers(boards, bingoNumbers[index])

        val matchingBoards = boards.filter { !isBingo(it) }
        if ((matchingBoards.size == 1)) {
            lastWiningBoard = matchingBoards[0]
        } else if (matchingBoards.isEmpty()) {
            println(lastWiningBoard!!.list.sumOf { it -> it.sumOf { it ?: 0 } } * bingoNumbers[index])
            break
        }
    }
}

private fun markMatchingNumbers(
    boards: MutableList<Board>, bingoNumber: Int
) {
    boards.forEach { board -> board.list.forEach { boardLine -> boardLine.replaceAll { value -> if (value == bingoNumber) null else value } } }
}

private fun isBingo(board: Board): Boolean {
    return isWiningRow(board) || isWiningColumn(board)
}

private fun isWiningRow(board: Board) = board.list.any { boardLine -> boardLine.all { it == null } }

fun isWiningColumn(board: Board): Boolean {
    for (index in 0 until board.list.first().size) {
        if (board.list.all { it[index] == null }) {
            return true
        }
    }
    return false
}

private fun setupBoard(lines: List<String>): MutableList<Board> {
    val boards = mutableListOf<Board>()
    lines.drop(1).filter { it.isNotEmpty() }.map { it ->
        it.trim().split("\\s+".toRegex()).map { it.toIntOrNull() }.toMutableList()
    }.chunked(5).forEach { boards.add(Board(it.toMutableList())) }
    return boards
}

data class Board(val list: List<MutableList<Int?>>)