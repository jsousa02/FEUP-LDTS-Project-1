package com.g0301.state

import com.g0301.Game
import com.g0301.controller.state.GameModeController
import com.g0301.gui.LanternaGUI
import spock.lang.Specification

class StatesTest extends Specification {

    LanternaGUI gui
    GameState gameState;
    Game game;

    def setup() {
        gui = Mock()
        gameState = Mock()
        game = new Game(60, 60, 20)
    }

    def "test game mode state"() {
        given:
            GameModeController gameModeController = new GameModeController(gameState, gui)
            GameModeState gameModeState = new GameModeState(game, gui)
        when:
            gameModeState.step()
        then:
            1 * gameModeController.step()
    }
}
