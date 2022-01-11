import model.Arena
import model.Position
import model.Trail
import spock.lang.Specification

class Collisions extends  Specification{
    def "Testing com.g0301.model.Wall Collision"(){
        given: "an arena and a position where we know there's a wall"
        Arena arena_test = new Arena(5,5)
        Position position_test= new Position(4,0)

        when: "the car moves to the wall position"
            arena_test.moveCar(position_test)

        then: "it should return true because there's a collision"
            arena_test.wallCollision()
    }

    def "Testing com.g0301.model.Car Collision with its own trails"(){
        given: "an arena"
            Arena arena_test = new Arena(5,5)
            Position initialPosition = arena_test.getCarPosition()
            Position finalPosition = new Position(2,2)
            arena_test.getBotTrailList().add(new Trail(1, 1, '-', '#FFFFFF'))

        when: "the car crashes against a trail"
            arena_test.moveCar(finalPosition)
            arena_test.getTrailList().add(new Trail(initialPosition.getX(), initialPosition.getY(),"-", "#FFFFFF"))
            arena_test.moveCar(initialPosition)

        then: "a collision should be detected"
            arena_test.trailCollision()
    }

    def "Testing Bot Collision with its own trails"() {
        given: "an arena and four positions that make a square"
            Arena arena = new Arena(120, 60)
            Position initialPosition = new Position(60, 30)
            Position secondPosition = new Position(61, 30)
            Position thirdPosition = new Position(61, 31)
            Position lastPosition = new Position(60, 31)
            arena.getTrailList().add(new Trail(1, 1, '-', '#FFFFFF'))

        when: "the bot movement makes a square"
            arena.moveBot(secondPosition)
            arena.getBotTrailList().add(new Trail(initialPosition.getX(), initialPosition.getY(), '-', '#FFFFFF'))
            arena.moveBot(thirdPosition)
            arena.getBotTrailList().add(new Trail(secondPosition.getX(), secondPosition.getY(), '-', '#FFFFFF'))
            arena.moveBot(lastPosition)
            arena.getBotTrailList().add(new Trail(thirdPosition.getX(), thirdPosition.getY(), '-', '#FFFFFF'))
            arena.moveBot(initialPosition)
            arena.getBotTrailList().add(new Trail(lastPosition.getX(), lastPosition.getY(), '-', '#FFFFFF'))

        then: "the bot will collide with its trails"
            arena.botTrailCollision()
    }
}
