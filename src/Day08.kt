import java.io.File

fun main() {
    val lines = File("src/Day08.txt").readLines().map { line ->
        val input = line.split(" | ")
        Input(input[0].split(" ").map { it.toCharArray().map { char -> char.toString() } },
            input[1].split(" ").map { it.toCharArray().map { char -> char.toString() } })
    }.toMutableList()

    solvePartOne(lines)

    solvePartTwo(lines)
}

private fun solvePartOne(lines: MutableList<Input>) {
    val matchingLength = setOf(2, 3, 4, 7)
    println(lines.sumOf { input -> input.outputValues.count { matchingLength.contains(it.size) } })
}

private fun solvePartTwo(lines: MutableList<Input>) {
    println(lines.sumOf { input ->
        val characters = mutableMapOf<Int, List<String>>()
        characters[1] = input.signalPatterns.first { it.size == 2 }
        characters[4] = input.signalPatterns.first { it.size == 4 }
        characters[7] = input.signalPatterns.first { it.size == 3 }
        characters[8] = input.signalPatterns.first { it.size == 7 }
        characters[2] = input.signalPatterns.first { patterns ->
            patterns.size == 5 && patterns.count { characters[4]!!.contains(it) } == 2
        }
        characters[3] = input.signalPatterns.first { it.size == 5 && it.containsAll(characters[1]!!) }
        characters[5] = input.signalPatterns.first { patterns ->
            patterns.size == 5 && patterns !in listOf(characters[2], characters[3])
        }
        characters[6] = input.signalPatterns.first { it.size == 6 && !it.containsAll(characters[1]!!) }
        characters[9] = input.signalPatterns.first { it.size == 6 && it.containsAll(characters[4]!!) }
        characters[0] = input.signalPatterns.first { it.size == 6 && it !in listOf(characters[6], characters[9]) }

        val sortedMap = characters.entries.associate { (k, v) -> v.sorted() to k }

        input.outputValues.map { sortedMap.getValue(it.sorted()) }.joinToString("").toInt()
    })
}

private data class Input(val signalPatterns: List<List<String>>, val outputValues: List<List<String>>)


