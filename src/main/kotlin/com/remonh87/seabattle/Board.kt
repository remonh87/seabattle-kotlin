package com.remonh87.seabattle

import arrow.core.*

class Board {

    private val columns = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
    private val rows = (1..10).toList().map { it.toString() }


    fun checkCoordinate(coordinate: Coordinate): Either<InvalidCoordinate, ValidCoordinate> {
        val move = ValidCoordinate.takeIf {
            columns.contains(coordinate.x) && rows.contains(coordinate.y)
        }

        return Either.cond(move != null, { ValidCoordinate }, { InvalidCoordinate })
    }

    fun areNeighbours(coordinate1: Coordinate, coordinate2: Coordinate): Boolean {
        val totaldiff = checkDiff(coordinate1.x, coordinate2.x, columns) + checkDiff(coordinate1.y, coordinate2.y, rows)

        return checkCoordinate(coordinate1).isRight() && checkCoordinate(coordinate2).isRight() && totaldiff == 1 || totaldiff == -1
    }

    private fun <T> checkDiff(value1: T, value2: T, collection: List<T>) =
        collection.indexOf(value1) - collection.indexOf(value2)

}

class Coordinate(x: Char, y: Char) {

    val x = x.toString()
    val y = y.toString()
}

object ValidCoordinate
object InvalidCoordinate

