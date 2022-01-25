package com.g0301.controller

import com.g0301.gui.Gui
import com.g0301.model.Player
import com.g0301.model.Position
import spock.lang.Specification

class PlayerControllers extends Specification {

    def "player1 moves up"() {
        given: "a car and its initial position"
            Player car = new Player(new Position(2, 2), '#FFFFFF')
            Player1Controller carController = new Player1Controller(car);
            Position initialPosition = car.getPosition()

        when: "the car moves up"
            Position finalPosition = carController.moveUp()

        then: "the y coordinate from the final position should be lower than the y coordinate from the initial position"
            finalPosition.getX() == initialPosition.getX() &&
                finalPosition.getY() < initialPosition.getY()
    }

    def "player 1 moves down"() {
        given: "a car and its initial position"
            Player car = new Player(new Position(2, 2), '#FFFFFF')
            Player1Controller carController = new Player1Controller(car);
            Position initialPosition = car.getPosition()

        when: "the car moves down"
            Position finalPosition = carController.moveDown()

        then: "the y coordinate from the final position should be greater than the y coordinate from the initial position"
            finalPosition.getX() == initialPosition.getX() &&
                finalPosition.getY() > initialPosition.getY()
    }

    def "player 1 moves left"() {
        given: "a car and its initial position"
            Player car = new Player(new Position(2, 2), '#FFFFFF')
            Player1Controller carController = new Player1Controller(car)
            Position initialPosition = car.getPosition()

        when: "the car moves left"
            Position finalPosition = carController.moveLeft()

        then: "the x coordinate from the final position should be lower than the x coordinate from the initial position"
            finalPosition.getX() < initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }

    def "player 1 moves right"() {
        given: "a car and its initial position"
            Player car = new Player(new Position(2, 2), '#FFFFFF')
            Player1Controller carController = new Player1Controller(car);
            Position initialPosition = car.getPosition()

        when: "the car moves right"
            Position finalPosition = carController.moveRight()

        then: "the x coordinate from the final position should be greater than the x coordinate from the initial position"
            finalPosition.getX() > initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }

    def "player 2 moves up"() {
        given: "a car and its initial position"
            Player car = new Player(new Position(2, 2), '#FFFFFF')
            Player2Controller carController = new Player2Controller(car);
            Position initialPosition = car.getPosition()

        when: "the car moves right"
            Position finalPosition = carController.moveRight()

        then: "the x coordinate from the final position should be greater than the x coordinate from the initial position"
            finalPosition.getX() > initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }

    def "player 2 moves down"() {
        given: "a car and its initial position"
            Player car = new Player(new Position(2, 2), '#FFFFFF')
            Player2Controller carController = new Player2Controller(car);
            Position initialPosition = car.getPosition()

        when: "the car moves down"
            Position finalPosition = carController.moveDown()

        then: "the y coordinate from the final position should be greater than the y coordinate from the initial position"
            finalPosition.getX() == initialPosition.getX() &&
                finalPosition.getY() > initialPosition.getY()
    }

    def "player 2 moves right"() {
        given: "a car and its initial position"
            Player car = new Player(new Position(2, 2), '#FFFFFF')
            Player2Controller carController = new Player2Controller(car);
            Position initialPosition = car.getPosition()

        when: "the car moves right"
            Position finalPosition = carController.moveRight()

        then: "the x coordinate from the final position should be greater than the x coordinate from the initial position"
            finalPosition.getX() > initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }

    def "player 2 moves left"() {
        given: "a car and its initial position"
            Player car = new Player(new Position(2, 2), '#FFFFFF')
            Player2Controller carController = new Player2Controller(car)
            Position initialPosition = car.getPosition()

        when: "the car moves left"
            Position finalPosition = carController.moveLeft()

        then: "the x coordinate from the final position should be lower than the x coordinate from the initial position"
            finalPosition.getX() < initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }

    def "test player 1 makeMovement method when the action is UP"() {
        given:
            Player car = new Player(new Position(2, 2), "#FFFFFF")
            Player1Controller player1Controller = new Player1Controller(car)
        when:
            player1Controller.makeMovement(Gui.ACTION.UP)
        then:
            player1Controller.makeMovement(Gui.ACTION.UP) >> player1Controller.moveUp()
    }

    def "test player 1 makeMovement method when the action is DOWN"() {
        given:
            Player car = new Player(new Position(2, 2), "#FFFFFF")
            Player1Controller player1Controller = new Player1Controller(car)
        when:
            player1Controller.makeMovement(Gui.ACTION.DOWN)
        then:
            player1Controller.makeMovement(Gui.ACTION.DOWN) >> player1Controller.moveDown()
    }

    def "test player 1 makeMovement method when the action is LEFT"() {
        given:
            Player car = new Player(new Position(2, 2), "#FFFFFF")
            Player1Controller player1Controller = new Player1Controller(car)
        when:
            player1Controller.makeMovement(Gui.ACTION.LEFT)
        then:
            player1Controller.makeMovement(Gui.ACTION.LEFT) >> player1Controller.moveLeft()
    }

    def "test player 1 makeMovement method when the action is RIGHT"() {
        given:
            Player car = new Player(new Position(2, 2), "#FFFFFF")
            Player1Controller player1Controller = new Player1Controller(car)
        when:
            player1Controller.makeMovement(Gui.ACTION.RIGHT)
        then:
            player1Controller.makeMovement(Gui.ACTION.RIGHT) >> player1Controller.moveRight()
    }

    def "test player 1 makeMovement method when the action is not valid"() {
        given:
            Player car = new Player(new Position(2, 2), "#FFFFFF")
            Player1Controller player1Controller = new Player1Controller(car)
        when:
            player1Controller.makeMovement(Gui.ACTION.ENTER)
        then:
            player1Controller.makeMovement(Gui.ACTION.ENTER) >> null
    }

    def "test player 2 makeMovement method when the action is UP"() {
        given:
            Player car = new Player(new Position(2, 2), "#FFFFFF")
            Player2Controller player2Controller = new Player2Controller(car)
        when:
            player2Controller.makeMovement(Gui.ACTION.P2UP)
        then:
            player2Controller.makeMovement(Gui.ACTION.P2UP) >> player2Controller.moveUp()
    }

    def "test player 2 makeMovement method when the action is DOWN"() {
        given:
            Player car = new Player(new Position(2, 2), "#FFFFFF")
            Player2Controller player2Controller = new Player2Controller(car)
        when:
            player2Controller.makeMovement(Gui.ACTION.P2DOWN)
        then:
            player2Controller.makeMovement(Gui.ACTION.P2DOWN) >> player2Controller.moveDown()
    }

    def "test player 2 makeMovement method when the action is RIGHT"() {
        given:
            Player car = new Player(new Position(2, 2), "#FFFFFF")
            Player2Controller player2Controller = new Player2Controller(car)
        when:
            player2Controller.makeMovement(Gui.ACTION.P2RIGHT)
        then:
            player2Controller.makeMovement(Gui.ACTION.P2RIGHT) >> player2Controller.moveRight()
    }

    def "test player 2 makeMovement method when the action is LEFT"() {
        given:
            Player car = new Player(new Position(2, 2), "#FFFFFF")
            Player2Controller player2Controller = new Player2Controller(car)
        when:
            player2Controller.makeMovement(Gui.ACTION.P2LEFT)
        then:
            player2Controller.makeMovement(Gui.ACTION.P2LEFT) >> player2Controller.moveLeft()
    }
}
