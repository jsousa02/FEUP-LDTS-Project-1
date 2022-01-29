package com.g0301.viewer.state

import com.g0301.gui.LanternaGUI
import com.g0301.model.Button
import com.g0301.model.OnePlayerArena
import com.g0301.model.Position
import com.g0301.model.SurvivalArena
import com.g0301.model.TwoPlayerArena
import com.g0301.viewer.ButtonViewer
import spock.lang.Specification

class StateViewers extends Specification {

    LanternaGUI gui
    List<Button> buttons =  new ArrayList<>()

    def setup() {
        gui = Mock()
        buttons.add(new Button(new Position(10, 10), "#FFFFFF", "#FF0000", "Button", 15, 8))
    }

    def "test menu viewer"() {
        given:
            MenuViewer menuViewer = new MenuViewer(gui, buttons)
        when:
            menuViewer.draw()
        then:
            1 * gui.drawBackground(gui.createTextGraphics(), "#000000")
            1 * gui.drawLogo(_, _)
            1 * gui.drawButton(_, _, _, _, _, _)
    }

    def "test game mode viewer"() {
        given:
            GameModeViewer gameModeViewer = new GameModeViewer(gui, buttons)
        when:
            gameModeViewer.draw()
        then:
            1 * gui.drawButton(_, _, _, _, _, _)
            1 * gui.drawLogo(_, _)
    }

    def "test instruction viewer"() {
        given:
            InstructionViewer instructionViewer = new InstructionViewer(gui, buttons)
        when:
            instructionViewer.draw()
        then:
            1 * gui.drawInstructions()
            1 * gui.drawButton(_, _, _, _, _, _)
    }

    def "test game over viewer"() {
        given:
            GameOverViewer gameOverViewer = new GameOverViewer(gui, Arrays.asList(new Button(new Position(10, 10), "#FFFFFF", "#FF0000", "Button", 15, 8)))
        when:
            gameOverViewer.draw()
        then:
            1 * gui.drawText(_, _, _, _)
    }

    def "test game win viewer"() {
        given:
            GameWinViewer gameWinViewer = new GameWinViewer(gui, Arrays.asList(new Button(new Position(10, 10), "#FFFFFF", "#FF0000", "Button", 15, 8)), "Player")
        when:
            gameWinViewer.draw()
        then:
            1 * gui.drawText(_, _, _, _)
    }

    def "test one player viewer"() {
        given:
            OnePlayerArena onePlayerArena = new OnePlayerArena(60, 60)
            OnePlayerViewer onePlayerViewer = new OnePlayerViewer(gui, Arrays.asList(new Button(new Position(10, 10), "#FFFFFF", "#FF0000", "Button", 15, 8)), onePlayerArena)
        when:
            onePlayerViewer.draw()
        then:
            1 * gui.drawBackground(gui.createTextGraphics(), "#000000")
            2 * gui.drawCar(_, _)
            _ * gui.drawTrail(_, _)
            onePlayerArena.getWalls().size() * gui.drawWall(_, _)
            onePlayerArena.getPortals().size() * gui.drawPortal(_, _, _)
    }

    def "test survival viewer"() {
        given:
            SurvivalArena survivalArena = new SurvivalArena(60, 60)
            SurvivalViewer survivalViewer = new SurvivalViewer(gui, Arrays.asList(new Button(new Position(10, 10), "#FFFFFF", "#FF0000", "Button", 15, 8)), survivalArena)
        when:
            survivalViewer.draw()
        then:
            1 * gui.drawBackground(gui.createTextGraphics(), "#000000")
            survivalArena.getWalls().size() * gui.drawWall(_ ,_)
            2 * gui.drawCar(_, _)
            _ * gui.drawTrail(_ ,_)
            survivalArena.getPortals().size() * gui.drawPortal(_, _, _)
    }

    def "test two player viewer"() {
        given:
            TwoPlayerArena twoPlayerArena = new TwoPlayerArena(60, 60)
            TwoPlayerViewer twoPlayerViewer = new TwoPlayerViewer(gui, Arrays.asList(new Button(new Position(10, 10), "#FFFFFF", "#FF0000", "Button", 15, 8)), twoPlayerArena)
        when:
            twoPlayerViewer.draw()
        then:
            1 * gui.drawBackground(gui.createTextGraphics(), "#000000")
            2 * gui.drawCar(_, _)
            _ * gui.drawTrail(_, _)
            twoPlayerArena.getWalls().size() * gui.drawWall(_, _)
            twoPlayerArena.getPortals().size() * gui.drawPortal(_, _, _)
    }
}
