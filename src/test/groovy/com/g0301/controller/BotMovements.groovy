package com.g0301.controller

import com.g0301.gui.Gui
import com.g0301.model.Bot
import com.g0301.model.Position
import spock.lang.Specification

class BotMovements extends Specification {

    Bot bot
    BotController botController

    def setup() {
        bot = new Bot(new Position(5, 5), "#FFFFFF")
        botController = new BotController(bot)
    }

    def "test bot move up"() {
        given:
            Position initialPosition = bot.getPosition()
        when:
            botController.moveUp()
        then:
            botController.moveUp() >> new Position(initialPosition.getX(), initialPosition.getY() - 1)
    }

    def "test bot move down"() {
        given:
            Position initialPosition = bot.getPosition()
        when:
            botController.moveDown()
        then:
            botController.moveDown() >> new Position(initialPosition.getX(), initialPosition.getY() + 1)
    }

    def "test bot move left"() {
        given:
            Position initialPosition = bot.getPosition()
        when:
            botController.moveLeft()
        then:
            botController.moveLeft() >> new Position(initialPosition.getX() - 1, initialPosition.getY())
    }

    def "test bot move right"() {
        given:
            Position initialPosition = bot.getPosition()
        when:
            botController.moveRight()
        then:
            botController.moveRight() >> new Position(initialPosition.getX() + 1, initialPosition.getY())
    }

    def "test make movement method when action is UP"() {
        when:
            botController.makeMovement(Gui.ACTION.UP)
        then:
            botController.makeMovement(Gui.ACTION.UP) >> botController.moveUp()
    }

    def "test make movement method when action is DOWN"() {
        when:
            botController.makeMovement(Gui.ACTION.DOWN)
        then:
            botController.makeMovement(Gui.ACTION.DOWN) >> botController.moveDown()
    }

    def "test make movement method when action is LEFT"() {
        when:
            botController.makeMovement(Gui.ACTION.LEFT)
        then:
            botController.makeMovement(Gui.ACTION.LEFT) >> botController.moveLeft()
    }

    def "test make movement method when action is RIGHT"() {
        when:
            botController.makeMovement(Gui.ACTION.RIGHT)
        then:
            botController.makeMovement(Gui.ACTION.RIGHT) >> botController.moveRight()
    }
}
