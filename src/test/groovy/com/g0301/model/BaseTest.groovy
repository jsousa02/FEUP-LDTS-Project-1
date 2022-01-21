package com.g0301.model
import spock.lang.Specification

class BaseTest extends Specification {

    def "Testing equals"() {
        given: "a car in a position"
        Player car = new Player(new Position(1, 1), '#FFFFFF')

        when: "a wall in the same position and a wall in a different position"
            Wall wallInSamePosition = new Wall(new Position(1, 1), "#FFFFFF")
            Wall wallInDifferentPosition = new Wall(new Position(1, 2), "#FFFFFF")

        then: "equals must return true if their positions are the same, false otherwise"
            car.getPosition().equals(wallInSamePosition.getPosition())
            car.getPosition().equals(wallInDifferentPosition.getPosition()) == false
    }

    def "Testing the 4 corners of the arena"() {
        given: "walls that are the corners of the arena"
            Wall wall_1 = new Wall(new Position(2, 0), "#FFFFFF")
            Wall wall_2 = new Wall(new Position(2, 2), "#FFFFFF")
            Wall wall_3 = new Wall(new Position(0, 2), "#FFFFFF")
            Wall wall_4 = new Wall(new Position(0, 0), "#FFFFFF")

        when: "the arena is created"
        TwoPlayerArena arena = new TwoPlayerArena(3, 3)

        then: "the walls should be created in the arena"
            arena.getWalls().contains(wall_1)
            arena.getWalls().contains(wall_2)
            arena.getWalls().contains(wall_3)
            arena.getWalls().contains(wall_4)
    }
}
