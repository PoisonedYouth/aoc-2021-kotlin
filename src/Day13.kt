import java.io.File


fun main() {
    val lines = File("src/Day13.txt").readLines()
    val coordinates = lines.takeWhile { it.isNotEmpty() }.map {
        val (x, y) = it.split(",")
        Point(x.toInt(), y.toInt())

    }
    val folds = lines.takeLastWhile { it.isNotEmpty() }.map {
        val (direction, value) = it.split(" ")[2].split("=")
        Fold(direction, value.toInt())
    }


    solvePartOne(coordinates, folds)

    solvePartTwo(coordinates, folds)
}

private fun solvePartOne(coordinates: List<Point>, folds: List<Fold>) {
    val fold = folds.first()
    for (i in coordinates.indices) {
        if (fold.direction == "x") {
            if (coordinates[i].x > fold.value) {
                coordinates[i].x = 2 * fold.value - coordinates[i].x
            }
        } else {
            if (coordinates[i].y > fold.value) {
                coordinates[i].y = 2 * fold.value - coordinates[i].y
            }
        }
    }
    println(coordinates.distinct().count())
}

private fun solvePartTwo(coordinates: List<Point>, folds: List<Fold>) {

    for (fold in folds) {
        for (i in coordinates.indices) {
            if (fold.direction == "x") {
                if (coordinates[i].x > fold.value) {
                    coordinates[i].x = 2 * fold.value - coordinates[i].x
                }
            } else {
                if (coordinates[i].y > fold.value) {
                    coordinates[i].y = 2 * fold.value - coordinates[i].y
                }
            }
        }
    }
    val x0 = coordinates.minOf { it.x }
    val y0 = coordinates.minOf { it.y }
    val x1 = coordinates.maxOf { it.x }
    val y1 = coordinates.maxOf { it.y }
    for (i in y0..y1) {
        for(j in x0..x1) {
            if(coordinates.distinct().contains(Point(j, i))) {
                print("#")
            } else {
                print(".")
            }
        }
        println()
    }

}

private data class Point(var x: Int, var y: Int)

private data class Fold(val direction: String, val value: Int)

