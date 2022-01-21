package com.g0301.model

import com.g0301.controller.state.OnePlayerController
import com.g0301.gui.Gui
import com.g0301.gui.LanternaGUI
import com.g0301.state.OnePlayerState
import spock.lang.Specification

class Boost extends Specification {

    LanternaGUI gui = Mock()

    def "test if boost bar size decreases when using boost"() {
        given: "a car and a boost bar"
            OnePlayerArena arena = new OnePlayerArena(20, 20)
            arena.getBoostBar().setHoldTime(15)
            OnePlayerController arenaController = new OnePlayerController(new OnePlayerState(, gui), gui)
        when: "the car is using boost"
            arenaController.keyPressed(Gui.ACTION.P1BOOST)
        then: "the boost bar should decrease"
            1  * arena.getBoostBar().decrease()
    }

    def "test if boost bar size increases after a while"() {
        given: "a car and a boost bar"
            OnePlayerArena arena = Stub()
            arena.getBoostBar().getReleaseTime() >> 5
            OnePlayerController arenaController = new OnePlayerController(new OnePlayerState(, gui) ,gui)
        when: "the car doesn't use the boost for a while"
            arenaController.keyPressed(Gui.ACTION.UP)
        then:
            1 * arena.getBoostBar().increase()
    }
}
