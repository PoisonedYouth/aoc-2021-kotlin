import java.io.File

const val ZERO = '0'
const val ONE = '1'

fun main() {
    val lines = File("src/Day03.txt").readLines()
    solveProblemOne(lines)

    solveProblemTwo(lines)
}

private fun solveProblemOne(lines: List<String>) {
    var gammaRate = ""
    var epsilonRate = ""
    for (index in 0 until lines.first().length) {
        lines.map { it[index] }.groupBy { it }.let {
            if (it[ZERO]!!.size > it[ONE]!!.size) {
                gammaRate += ZERO
                epsilonRate += ONE
            } else {
                gammaRate += ONE
                epsilonRate += ZERO
            }
        }

    }
    println(gammaRate.toInt(2) * epsilonRate.toInt(2))
}

private fun solveProblemTwo(lines: List<String>) {
    var oxygenRatingList = lines
    var scrubberRatingList = lines
    for (index in 0 until lines.first().length) {
        if (oxygenRatingList.size > 1) {
            val groups = oxygenRatingList.map { it[index] }.groupBy { it }
            oxygenRatingList = oxygenRatingList.filter { it[index] == if (groups[ZERO]!!.size > groups[ONE]!!.size) ZERO else ONE }
        }
        if (scrubberRatingList.size > 1) {
            val groups = scrubberRatingList.map { it[index] }.groupBy { it }
            scrubberRatingList = scrubberRatingList.filter { it[index] == if (groups[ZERO]!!.size > groups[ONE]!!.size) ONE else ZERO }

        }
    }
    println(oxygenRatingList[0].toInt(2) * scrubberRatingList[0].toInt(2))
}

