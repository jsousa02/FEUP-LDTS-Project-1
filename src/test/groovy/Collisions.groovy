import com.g0301.controller.CarController
import com.g0301.model.Arena
import com.g0301.model.Portal
import com.g0301.model.Position
import com.g0301.model.Trail
import spock.lang.Specification

class Collisions extends  Specification{
    def "Testing Wall Collision"(){
        given: "an arena and a position where we know there's a wall"
        Arena arena_test = new Arena(60,60)
        CarController carController = new CarController(arena_test.getCar());
        Position position_test = new Position(0,0)

        when: "the car moves to the wall position"
            carController.moveCar(position_test)

        then: "it should return true because there's a collision"
            arena_test.wallCollision()
    }

    def "Testing Car Collision with its own trails"(){
        given: "an arena"
            Arena arena_test = new Arena(60,60)
            CarController carController = new CarController(arena_test.getCar());
            Position initialPosition = arena_test.getCar().getPosition()
            Position finalPosition = new Position(20,31)

        when: "the car crashes against a trail"
            carController.moveCar(finalPosition)
            arena_test.getCar().getTrailList().add(new Trail(new Position(initialPosition.getX(), initialPosition.getY()),"#FFFFFF"))
            carController.moveCar(initialPosition)

        then: "a collision should be detected"
            arena_test.getCar().collisionWithOwnTrail()
    }

    def "Testing Bot Collision with its own trails"() {
        given: "an arena and four positions that make a square"
            Arena arena = new Arena(60, 60)
            Position initialPosition = new Position(20, 30)
            Position secondPosition = new Position(21, 30)
            Position thirdPosition = new Position(21, 31)
            Position lastPosition = new Position(20, 31)
            CarController carController = new CarController(arena.getCar());
            arena.getCar().getTrailList().add(new Trail(new Position(1, 1), '#FFFFFF'));

        when: "the bot movement makes a square"
            carController.moveCar(secondPosition)
            arena.getCar().getTrailList().add(new Trail(new Position(initialPosition.getX(), initialPosition.getY()), '#FFFFFF'))
            carController.moveCar(thirdPosition)
            arena.getCar().getTrailList().add(new Trail(new Position(secondPosition.getX(), secondPosition.getY()), '#FFFFFF'))
            carController.moveCar(lastPosition)
            arena.getCar().getTrailList().add(new Trail(new Position(thirdPosition.getX(), thirdPosition.getY()), '#FFFFFF'))
            carController.moveCar(initialPosition)
            arena.getCar().getTrailList().add(new Trail(new Position(lastPosition.getX(), lastPosition.getY()), '#FFFFFF'))

        then: "the bot will collide with its trails"
            arena.getCar().collisionWithOwnTrail()
    }

    def "enter a portal"() {
        given: "an arena and a portal"
            Arena arena = new Arena(60, 60)
            CarController carController = new CarController(arena.getCar())
            Portal portal = new Portal(new Position(55, 30), new Position(20, 30), "#FF03FF")
            arena.getPortals().add(portal)
        when: "the player crosses a portal"
            carController.moveCar(new Position(55, 30))
            arena.enterPortalThroughStart()
        then: "the player's position should be equals to the exit position of the portal"
            carController.getCar().getPosition().equals(portal.getSecondPosition())
    }

    def "enter a portal by its exit"() {
        given: "an arena and a portal"
            Arena arena = new Arena(60, 60)
            CarController carController = new CarController(arena.getCar())
            Portal portal = new Portal(new Position(55, 30), new Position(20, 30), "#FF03FF")
            arena.getPortals().add(portal)
        when: "the player crosses the portal"
            carController.moveCar(new Position(20, 30))
            arena.enterPortalThroughExit()
        then: "the player's position should be equals to the start position of the portal"
            carController.getCar().getPosition().equals(portal.getPosition())
    }
}
