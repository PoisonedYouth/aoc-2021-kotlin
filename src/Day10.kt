import java.io.File

fun main() {
    val lines = File("src/Day10.txt").readLines()
    val elements = mapOf('{' to '}', '(' to ')', '[' to ']', '<' to '>')

    solvePartOne(lines, elements)

    solvePartTwo(lines, elements)
}

private fun solvePartOne(lines: List<String>, elements: Map<Char, Char>) {
    var score = 0
    for (line in lines) {
        val deque = ArrayDeque<Char>()
        for (character in line) {
            if (elements.keys.contains(character)) {
                deque.addFirst(character)
            } else {
                val element = deque.removeFirst()
                if (character != elements[element]) {
                    score += calculateScoreChecker(character)
                    break
                }
            }
        }
    }
    println(score)
}

private fun solvePartTwo(lines: List<String>, elements: Map<Char, Char>) {
    val scoreList = mutableListOf<Long>()
    for (line in lines) {
        val deque = ArrayDeque<Char>()
        for (character in line) {
            if (elements.keys.contains(character)) {
                deque.addFirst(character)
            } else {
                val element = deque.removeFirst()
                if (character != elements[element]) {
                    deque.clear()
                    break
                }
            }
        }
        if (deque.isNotEmpty()) {
            val missingElements = mutableListOf<Char>()
            var score = 0L
            while (deque.isNotEmpty()) {
                val element = deque.removeFirst()
                val matchingElement = elements[element]!!
                missingElements.add(matchingElement)
                score = score * 5 + calculateScoreCompleter(matchingElement)
            }
            scoreList.add(score)
        }
    }
    println(scoreList.sorted()[scoreList.size / 2])
}

fun calculateScoreChecker(character: Char): Int {
    return when (character) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        '>' -> 25137
        else -> throw IllegalArgumentException("Invalid element!")
    }
}

fun calculateScoreCompleter(character: Char): Int {
    return when (character) {
        ')' -> 1
        ']' -> 2
        '}' -> 3
        '>' -> 4
        else -> throw IllegalArgumentException("Invalid element!")
    }
}



