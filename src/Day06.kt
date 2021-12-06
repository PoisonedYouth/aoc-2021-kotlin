import java.io.File

fun main() {
    val lines = File("src/Day06.txt").readText().split(",").map { it.toInt() }.toMutableList()

    solvePartOne(lines)

    solvePartTwo(lines)
}

fun solvePartOne(lines: MutableList<Int>) {
    repeat(80) {
        for (index in lines.indices) {
            val value = lines[index]
            if (value > 0) {
                lines[index] = value - 1
            } else if (value == 0) {
                lines[index] = 6
                lines.add(8)
            }
        }

    }
    println(lines.size)
}

fun solvePartTwo(lines: MutableList<Int>) {
    val map = lines.groupBy { it }.map { (k, v) -> k to v.size.toLong() }.toMap().toMutableMap().toSortedMap()
    repeat(256) {
        val zero = map.getOrDefault(0, 0L)
        val one = map.getOrDefault(1, 0L)
        val two = map.getOrDefault(2, 0L)
        val three = map.getOrDefault(3, 0L)
        val four = map.getOrDefault(4, 0L)
        val five = map.getOrDefault(5, 0L)
        val six = map.getOrDefault(6, 0L)
        val seven = map.getOrDefault(7, 0L)
        val eight = map.getOrDefault(8, 0L)

        map[0] = one
        map[1] = two
        map[2] = three
        map[3] = four
        map[4] = five
        map[5] = six
        map[6] = seven + zero
        map[7] = eight
        map[8] = zero
    }
    println(map.values.sumOf { it })
}

