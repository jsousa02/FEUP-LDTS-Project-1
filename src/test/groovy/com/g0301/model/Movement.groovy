package com.g0301.model

import com.g0301.controller.Player1Controller
import com.g0301.controller.state.OnePlayerController
import com.g0301.gui.Gui

import com.g0301.controller.CarController

import spock.lang.Specification

class Movement extends Specification {

    /**def "car is moving when no key is being pressed in the beginning of the game"() {
>>>>>>> feat-boost_bar
        given: "an arena and the initial position of the car"
            OnePlayerArena arena = new OnePlayerArena(60, 60)
            OnePlayerController arenaController = new OnePlayerController(arena)
            Position initialPosition = arena.getPlayer1().getPosition()
            Gui.ACTION previousMovement = arena.getPlayer1().getPreviousMovement()

        when: "no key is being pressed"
            arenaController.step(previousMovement)

        then: "the car should be always moving to the right"
<<<<<<< HEAD
            arena.getPlayer1().getPosition().getY() == initialPosition.getY()
    }
=======
            arena.getCar().getPosition().getY() == initialPosition.getY()
    }*/

    def "car moves up"() {
        given: "a car and its initial position"
            Player car = new Player(new Position(2, 2), '#FFFFFF')
            CarController carController = new Player1Controller(car);
            Position initialPosition = car.getPosition()

        when: "the car moves up"
            Position finalPosition = carController.moveUp()

        then: "the y coordinate from the final position should be lower than the y coordinate from the initial position"
            finalPosition.getX() == initialPosition.getX() &&
                    finalPosition.getY() < initialPosition.getY()
    }

    def "car moves down"() {
        given: "a car and its initial position"
            Player car = new Player(new Position(2, 2), '#FFFFFF')
            CarController carController = new Player1Controller(car);
            Position initialPosition = car.getPosition()

        when: "the car moves down"
            Position finalPosition = carController.moveDown()

        then: "the y coordinate from the final position should be greater than the y coordinate from the initial position"
            finalPosition.getX() == initialPosition.getX() &&
                        finalPosition.getY() > initialPosition.getY()
    }

    def "car moves left"() {
        given: "a car and its initial position"
            Player car = new Player(new Position(2, 2), '#FFFFFF')
            CarController carController = new Player1Controller(car)
            Position initialPosition = car.getPosition()

        when: "the car moves left"
            Position finalPosition = carController.moveLeft()

        then: "the x coordinate from the final position should be lower than the x coordinate from the initial position"
            finalPosition.getX() < initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }

    def "car moves right"() {
        given: "a car and its initial position"
            Player car = new Player(new Position(2, 2), '#FFFFFF')
            CarController carController = new Player1Controller(car);
            Position initialPosition = car.getPosition()

        when: "the car moves right"
            Position finalPosition = carController.moveRight()

        then: "the x coordinate from the final position should be greater than the x coordinate from the initial position"
            finalPosition.getX() > initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }
}
