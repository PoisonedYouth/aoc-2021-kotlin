import java.io.File
import java.util.*

fun main() {
    val lines = File("src/Day10.txt").readLines()
    val elements = mapOf('{' to '}', '(' to ')', '[' to ']', '<' to '>')

    solvePartOne(lines, elements)

    solvePartTwo(lines, elements)
}

private fun solvePartOne(lines: List<String>, elements: Map<Char, Char>) {
    var score = 0
    for (line in lines) {
        val stack = Stack<Char>()
        for (character in line) {
            if (elements.keys.contains(character)) {
                stack.push(character)
            } else {
                val element = stack.pop()
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
        val stack = Stack<Char>()
        for (character in line) {
            if (elements.keys.contains(character)) {
                stack.push(character)
            } else {
                val element = stack.pop()
                if (character != elements[element]) {
                    stack.clear()
                    break
                }
            }
        }
        if (stack.isNotEmpty()) {
            val missingElements = mutableListOf<Char>()
            var score = 0L
            while (stack.isNotEmpty()) {
                val element = stack.pop()
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



