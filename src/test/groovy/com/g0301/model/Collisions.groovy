package com.g0301.model

import com.g0301.controller.BotController
import com.g0301.controller.BotMovements
import com.g0301.controller.Player1Controller
import com.g0301.gui.Gui
import com.g0301.controller.CarController
import spock.lang.Specification

class Collisions extends Specification {

    def "Testing Wall Collision"() {
        given: "an arena and a position where we know there's a wall"
            OnePlayerArena arena_test = new OnePlayerArena(60,60)
            Player1Controller carController = new Player1Controller(arena_test.getPlayer1());
            Position position_test = new Position(0,5)

        when: "the car moves to the wall position"
            carController.moveCar(position_test)

        then: "it should return true because there's a collision"
            arena_test.wallCollision()
    }

    def "Testing Car Collision with its own trails"() {
        given: "an arena"
            TwoPlayerArena arena_test = new TwoPlayerArena(60,60)
            CarController carController = new Player1Controller(arena_test.getPlayer1())
            Position initialPosition = arena_test.getPlayer1().getPosition()
            Position finalPosition = new Position(20,31)

        when: "the car crashes against a trail"
            carController.moveCar(finalPosition)
            arena_test.getPlayer1().getTrailList().add(new Trail(new Position(initialPosition.getX(), initialPosition.getY()),"#FFFFFF"))
            carController.moveCar(initialPosition)

        then: "a collision should be detected"
            arena_test.getPlayer1().collisionWithOwnTrail()
    }

    def "Testing Bot Collision with its own trails"() {
        given: "an arena and four positions that make a square"
            OnePlayerArena arena = new OnePlayerArena(60, 60)
            Position initialPosition = new Position(20, 30)
            Position secondPosition = new Position(21, 30)
            Position thirdPosition = new Position(21, 31)
            Position lastPosition = new Position(20, 31)
            BotController carController = new BotController(arena.getBot());
            arena.getBot().getTrailList().add(new Trail(new Position(1, 1), '#FFFFFF'));

        when: "the bot movement makes a square"
            carController.moveCar(secondPosition)
            arena.getBot().getTrailList().add(new Trail(new Position(initialPosition.getX(), initialPosition.getY()), '#FFFFFF'))
            carController.moveCar(thirdPosition)
            arena.getBot().getTrailList().add(new Trail(new Position(secondPosition.getX(), secondPosition.getY()), '#FFFFFF'))
            carController.moveCar(lastPosition)
            arena.getBot().getTrailList().add(new Trail(new Position(thirdPosition.getX(), thirdPosition.getY()), '#FFFFFF'))
            carController.moveCar(initialPosition)
            arena.getBot().getTrailList().add(new Trail(new Position(lastPosition.getX(), lastPosition.getY()), '#FFFFFF'))

        then: "the bot will collide with its trails"
            arena.getBot().collisionWithOwnTrail()
    }

    def "enter a portal"() {
        given: "an arena and a portal"
            TwoPlayerArena arena = new TwoPlayerArena(60, 60)
            Player1Controller carController = new Player1Controller(arena.getPlayer1())
            Portal portal = new Portal(new Position(55, 30), new Position(20, 30), "#FF03FF")
            arena.getPortals().add(portal)
        when: "the player crosses a portal"
            carController.moveCar(new Position(55, 30))
            arena.enterPortalThroughStart(Gui.ACTION.RIGHT, carController)
        then: "the player's position should be equals to the exit position of the portal"
            carController.getCar().getPosition().equals(new Position(portal.getSecondPosition().getX() + 1, portal.getPosition().getY()))
    }

    def "enter a portal by its exit"() {
        given: "an arena and a portal"
            TwoPlayerArena arena = new TwoPlayerArena(60, 60)
            Player1Controller carController = new Player1Controller(arena.getPlayer1())
            Portal portal = new Portal(new Position(55, 30), new Position(20, 30), "#FF03FF")
            arena.getPortals().add(portal)
        when: "the player crosses the portal"
            carController.moveCar(new Position(20, 30))
            arena.enterPortalThroughExit(Gui.ACTION.RIGHT, carController)
        then: "the player's position should be equals to the start position of the portal"
            carController.getCar().getPosition().equals(new Position(portal.getPosition().getX() + 1, portal.getPosition().getY()))
    }

    def "Testing Player's car collision with bot trail"() {
        given: "an arena,a car and a bot"
            OnePlayerArena arena= new OnePlayerArena(60,60)
        when: "the player crashes against bot trail"
            Trail trail = new Trail(new Position(20,20),"#FFFFFF")
            arena.getBot().trailList.add(trail)
            arena.getPlayer1().setPosition(new Position(20,20))
        then: "the car will collide with the bot trail"
            arena.playerCollisionWithBotTrail()
    }

    def "Testing bot collision with player trail"() {
        given: "an arena,a car and a bot"
            OnePlayerArena arena= new OnePlayerArena(60,60)
        when: "the player crashes against bot trail"
            arena.getPlayer1().getTrailList().add(new Trail(new Position(20,20),"#FFFFFF"))
            arena.getBot().setPosition(new Position(20,20))
        then: "the car will collide with the bot trail"
            arena.botCollisionWithCarTrail()
    }
}
