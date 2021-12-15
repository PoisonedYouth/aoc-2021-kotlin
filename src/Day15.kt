import java.io.File
import java.util.*


fun main() {
	val lines = File("src/Day15.txt").readLines()

	val width: Int = lines.first().length
	val height: Int = lines.size
	val risks = IntArray(width * lines.size) { lines[it / width][it % width].digitToInt() }

	solvePartOne(width, height, risks)

	solvePartTwo(width * 5,
		height * 5,
		IntArray(25 * risks.size) {
			val x = it % (5 * width)
			val y = it / (5 * width)
			(risks[y % (risks.size / width) * width + x % width] - 1 + x / width + y / height) % 9 + 1
		}
	)
}

private fun solvePartOne(width: Int, height: Int, risks: IntArray) {
	findBestDirection(risks, width, height)
}

private fun solvePartTwo(width: Int, height: Int, risks: IntArray) {
	findBestDirection(risks, width, height)
}

private fun findBestDirection(risks: IntArray, width: Int, height: Int) {
	val bests = IntArray(risks.size) { Int.MAX_VALUE }
	bests[0] = 0
	val queue = PriorityQueue(compareBy(Pair<Int, Int>::first))
	queue.add(0 to 0)
	val best: Int
	while (true) {
		val i = queue.remove().second
		val c0 = bests[i]
		if (i == risks.lastIndex) {
			best = c0
			break
		}
		val x0 = i % width
		val y0 = i / width
		for ((x1, y1) in arrayOf(x0 - 1 to y0, x0 to y0 - 1, x0 to y0 + 1, x0 + 1 to y0)) {
			if (x1 !in 0 until width || y1 !in 0 until height) continue
			val j = y1 * width + x1
			val c1 = c0 + risks[j]
			if (c1 < bests[j]) {
				bests[j] = c1
				queue.add(c1 to j)
			}
		}
	}
	println(best)
}
