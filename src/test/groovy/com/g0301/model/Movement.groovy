package com.g0301.model

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
}
