package com.g0301.controller.state

import com.g0301.Game
import com.g0301.gui.LanternaGUI
import com.g0301.state.GameModeState
import com.g0301.state.InstructionState
import com.g0301.state.MenuState
import spock.lang.Specification

class MenuControllerTest extends Specification {

    LanternaGUI gui

    def setup() {
        gui = Mock()
    }

    def "test step method"() {
        given:
            Game game = Mock()
            MenuState menuState = new MenuState(game, gui)
            MenuController menuController = new MenuController(menuState, gui)
        when:
            menuController.step()
        then:
            1 * gui.clear()
            1 * gui.refresh()
            3 * gui.drawButton(_,_,_,_,_,_)
    }

    def "test getNextState when selected index is 0"() {
        given:
            MenuState menuState = Stub()
            MenuController menuController = new MenuController(menuState, gui)
            menuState.getSelectedIndex() >> 0
        when:
            menuController.getNextState()
        then:
            menuController.nextState instanceof GameModeState
    }

    def "test getNextState when selected index is 1"() {
        given:
            MenuState menuState = Stub()
            MenuController menuController = new MenuController(menuState, gui)
            menuState.getSelectedIndex() >> 1
        when:
            menuController.getNextState()
        then:
            menuController.nextState instanceof InstructionState
    }

    def "test getNextState when selected index is 2"() {
        given:
            MenuState menuState = Stub()
            MenuController menuController = new MenuController(menuState, gui)
            menuState.getSelectedIndex() >> 2
        when:
            menuController.getNextState()
        then:
            menuController.nextState == null
    }
}
