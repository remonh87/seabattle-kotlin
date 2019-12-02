package com.remonh87.seabattle

import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec
import java.lang.AssertionError

class BoardTest : FunSpec({

    context("Board") {
        val board = Board()

        context("Check valid coordinate") {

            test("Check valid coordinates") {

                board.checkCoordinate(Coordinate('A', '2')).fold(
                    { throw AssertionError("Invalid") },
                    { it }) shouldBe ValidCoordinate

            }

            test("Check invalid coordinate") {

                board.checkCoordinate(Coordinate('Z', '2')).fold(
                    { it },
                    { throw AssertionError("Invalid") }) shouldBe InvalidCoordinate
            }
        }

        context("Check neighbours") {


            test("Comparing 2 neighbouring coordinates on column") {
                board.areNeighbours(Coordinate('A', '2'), Coordinate('B', '2')) shouldBe true
            }


            test("Comparing 2 neighbouring coordinates on row") {
                board.areNeighbours(Coordinate('A', '2'), Coordinate('A', '3')) shouldBe true

            }

            test("Comparing 2  non neighbouring coordinates on column") {
               board.areNeighbours(Coordinate('A', '2'), Coordinate('C', '2')) shouldBe false
            }

            test("Comparing 2  non neighbouring coordinates on row") {
                board.areNeighbours(Coordinate('A', '2'), Coordinate('A', '4')) shouldBe false
            }

            test("Comparing 2  cell that are diagonal connected") {
              board.areNeighbours(Coordinate('A', '2'), Coordinate('B', '3')) shouldBe false

            }

            test("Comparing 1 invalid coordinate with a valid coordinate") {
                board.areNeighbours(Coordinate('A', '2'), Coordinate('Z', '2')) shouldBe false
            }
        }
    }

})