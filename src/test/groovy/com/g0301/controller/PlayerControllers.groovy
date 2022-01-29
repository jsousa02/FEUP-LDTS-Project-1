package com.g0301.controller

import com.g0301.gui.Gui
import com.g0301.model.Player
import com.g0301.model.Position
import spock.lang.Specification

class PlayerControllers extends Specification {

    Player car
    Player1Controller carController
    Player2Controller car2Controller

    def setup() {
        car = new Player(new Position(2, 2), '#FFFFFF')
        carController = new Player1Controller(car)
        car2Controller = new Player2Controller(car);
    }

    def "player1 moves up"() {
        given: "a car and its initial position"
            Position initialPosition = car.getPosition()

        when: "the car moves up"
            Position finalPosition = carController.moveUp()

        then: "the y coordinate from the final position should be lower than the y coordinate from the initial position"
            finalPosition.getX() == initialPosition.getX() &&
                finalPosition.getY() < initialPosition.getY()
    }

    def "player 1 moves down"() {
        given: "a car and its initial position"
            Position initialPosition = car.getPosition()

        when: "the car moves down"
            Position finalPosition = carController.moveDown()

        then: "the y coordinate from the final position should be greater than the y coordinate from the initial position"
            finalPosition.getX() == initialPosition.getX() &&
                finalPosition.getY() > initialPosition.getY()
    }

    def "player 1 moves left"() {
        given: "a car and its initial position"
            Position initialPosition = car.getPosition()

        when: "the car moves left"
            Position finalPosition = carController.moveLeft()

        then: "the x coordinate from the final position should be lower than the x coordinate from the initial position"
            finalPosition.getX() < initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }

    def "player 1 moves right"() {
        given: "a car and its initial position"
            Position initialPosition = car.getPosition()

        when: "the car moves right"
            Position finalPosition = carController.moveRight()

        then: "the x coordinate from the final position should be greater than the x coordinate from the initial position"
            finalPosition.getX() > initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }

    def "player 2 moves up"() {
        given: "a car and its initial position"
            Position initialPosition = car.getPosition()

        when: "the car moves right"
            Position finalPosition = car2Controller.moveRight()

        then: "the x coordinate from the final position should be greater than the x coordinate from the initial position"
            finalPosition.getX() > initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }

    def "player 2 moves down"() {
        given: "a car and its initial position"
            Position initialPosition = car.getPosition()

        when: "the car moves down"
            Position finalPosition = car2Controller.moveDown()

        then: "the y coordinate from the final position should be greater than the y coordinate from the initial position"
            finalPosition.getX() == initialPosition.getX() &&
                finalPosition.getY() > initialPosition.getY()
    }

    def "player 2 moves right"() {
        given: "a car and its initial position"

            Position initialPosition = car.getPosition()

        when: "the car moves right"
            Position finalPosition = car2Controller.moveRight()

        then: "the x coordinate from the final position should be greater than the x coordinate from the initial position"
            finalPosition.getX() > initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }

    def "player 2 moves left"() {
        given: "a car and its initial position"
            Position initialPosition = car.getPosition()

        when: "the car moves left"
            Position finalPosition = car2Controller.moveLeft()

        then: "the x coordinate from the final position should be lower than the x coordinate from the initial position"
            finalPosition.getX() < initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }

    def "test player 1 makeMovement method when the action is UP"() {
        when:
            carController.makeMovement(Gui.ACTION.UP)
        then:
            carController.makeMovement(Gui.ACTION.UP) >> carController.moveUp()
    }

    def "test player 1 makeMovement method when the action is DOWN"() {
        when:
            carController.makeMovement(Gui.ACTION.DOWN)
        then:
            carController.makeMovement(Gui.ACTION.DOWN) >> carController.moveDown()
    }

    def "test player 1 makeMovement method when the action is LEFT"() {
        when:
            carController.makeMovement(Gui.ACTION.LEFT)
        then:
            carController.makeMovement(Gui.ACTION.LEFT) >> carController.moveLeft()
    }

    def "test player 1 makeMovement method when the action is RIGHT"() {
        when:
            carController.makeMovement(Gui.ACTION.RIGHT)
        then:
            carController.makeMovement(Gui.ACTION.RIGHT) >> carController.moveRight()
    }

    def "test player 1 makeMovement method when the action is not valid"() {
        when:
            carController.makeMovement(Gui.ACTION.ENTER)
        then:
            carController.makeMovement(Gui.ACTION.ENTER) >> null
    }

    def "test player 2 makeMovement method when the action is UP"() {
        when:
            carController.makeMovement(Gui.ACTION.P2UP)
        then:
            carController.makeMovement(Gui.ACTION.P2UP) >> carController.moveUp()
    }

    def "test player 2 makeMovement method when the action is DOWN"() {
        when:
            carController.makeMovement(Gui.ACTION.P2DOWN)
        then:
            carController.makeMovement(Gui.ACTION.P2DOWN) >> carController.moveDown()
    }

    def "test player 2 makeMovement method when the action is RIGHT"() {
        when:
            carController.makeMovement(Gui.ACTION.P2RIGHT)
        then:
            carController.makeMovement(Gui.ACTION.P2RIGHT) >> carController.moveRight()
    }

    def "test player 2 makeMovement method when the action is LEFT"() {
        when:
            carController.makeMovement(Gui.ACTION.P2LEFT)
        then:
            carController.makeMovement(Gui.ACTION.P2LEFT) >> carController.moveLeft()
    }
}
