import java.io.File

fun main() {
    val lines = File("src/Day11.txt").readLines().map { line -> line.toList().map { it.digitToInt() } }

    solvePartOne(lines)

    solvePartTwo(lines)
}

private fun solvePartOne(lines: List<List<Int>>) {
    var flashes = 0
    val field = initField(lines)
    repeat(100) {
        initStep(field)
        var proceed = true
        while (proceed) {
            for (i in field.indices) {
                for (j in 0 until field[i].size) {
                    val element = field[i][j]
                    if (element.value > 9) {
                        if (!element.flashed) {
                            flashes++
                        }
                        field[i][j].flashed = true
                        updateAdjacents(element, field)
                    }
                }
            }
            resetFlashedElements(field)
            if (field.all { line -> line.none { it.value > 9 } }) {
                proceed = false
            }
        }
    }
    println(flashes)
}

private fun solvePartTwo(lines: List<List<Int>>) {
    val field = initField(lines)
    var iteration = 1
    while (true) {
        initStep(field)
        var proceed = true
        while (proceed) {
            executeStep(field)
            resetFlashedElements(field)
            if (field.all { line -> line.none { it.value > 9 } }) {
                proceed = false
            }
        }
        if (field.all { line -> line.all { it.flashed } }) {
            println(iteration)
            break
        } else {
            iteration++
        }
    }
}

private fun resetFlashedElements(field: List<List<Element>>) {
    for (i in field.indices) {
        for (j in 0 until field[i].size) {
            if (field[i][j].flashed) {
                field[i][j].value = 0
            }
        }
    }
}

private fun updateAdjacents(element: Element, field: List<List<Element>>) {
    if (element.x > 0) {
        val value = field[element.x - 1][element.y]
        field[element.x - 1][element.y].value = value.value + 1
    }
    if (element.x < field.size - 1) {
        val value = field[element.x + 1][element.y]
        field[element.x + 1][element.y].value = value.value + 1
    }
    if (element.y > 0) {
        val value = field[element.x][element.y - 1]
        field[element.x][element.y - 1].value = value.value + 1
    }
    if (element.y < field[element.x].size - 1) {
        val value = field[element.x][element.y + 1]
        field[element.x][element.y + 1].value = value.value + 1
    }
    if (element.x > 0 && element.y > 0) {
        val value = field[element.x - 1][element.y - 1]
        field[element.x - 1][element.y - 1].value = value.value + 1
    }
    if (element.x < field.size - 1 && element.y < field[element.x].size - 1) {
        val value = field[element.x + 1][element.y + 1]
        field[element.x + 1][element.y + 1].value = value.value + 1
    }
    if (element.x < field.size - 1 && element.y > 0) {
        val value = field[element.x + 1][element.y - 1]
        field[element.x + 1][element.y - 1].value = value.value + 1
    }
    if (element.x > 0 && element.y < field[element.x].size - 1) {
        val value = field[element.x - 1][element.y + 1]
        field[element.x + -1][element.y + 1].value = value.value + 1
    }
}

private fun initField(lines: List<List<Int>>): List<List<Element>> {
    val result = mutableListOf<MutableList<Element>>()
    for (i in lines.indices) {
        result.add(mutableListOf())
        for (j in 0 until lines[i].size) {
            result[i].add(Element(lines[i][j], i, j, false))
        }
    }
    return result.map { it.toList() }.toList()
}

private fun executeStep(field: List<List<Element>>) {
    for (i in field.indices) {
        for (j in 0 until field[i].size) {
            val element = field[i][j]
            if (element.value > 9) {
                field[i][j].flashed = true
                updateAdjacents(element, field)
            }
        }
    }
}

private fun initStep(field: List<List<Element>>) {
    for (i in field.indices) {
        for (j in 0 until field[i].size) {
            field[i][j].flashed = false
            field[i][j].value = field[i][j].value + 1
        }
    }
}

private data class Element(var value: Int, val x: Int, val y: Int, var flashed: Boolean)

