import com.g0301.gui.Gui
import com.g0301.controller.ArenaController
import com.g0301.controller.CarController
import com.g0301.model.Arena
import com.g0301.model.Car
import com.g0301.model.Position
import spock.lang.Specification

class Movement extends Specification {
    def "car is moving when no key is being pressed in the beginning of the game"() {
        given: "an arena and the initial position of the car"
        Arena arena = new Arena(60, 60)
        ArenaController arenaController = new ArenaController(arena)
        Position initialPosition = arena.getCar().getPosition()
        Gui.ACTION previousMovement = arena.getCar().getPreviousMovement()

        when: "no key is being pressed"
            arenaController.step(previousMovement)

        then: "the car should be always moving to the right"
            arena.getCar().getPosition().getY() == initialPosition.getY()
    }

    def "car moves up"() {
        given: "a car and its initial position"
            Car car = new Car(new Position(2, 2), '#FFFFFF')
            CarController carController = new CarController(car);
            Position initialPosition = car.getPosition()

        when: "the car moves up"
            Position finalPosition = carController.moveUp()

        then: "the y coordinate from the final position should be lower than the y coordinate from the initial position"
            finalPosition.getX() == initialPosition.getX() &&
                    finalPosition.getY() < initialPosition.getY()
    }

    def "car moves down"() {
        given: "a car and its initial position"
            Car car = new Car(new Position(2, 2), '#FFFFFF')
            CarController carController = new CarController(car);
            Position initialPosition = car.getPosition()

        when: "the car moves down"
            Position finalPosition = carController.moveDown()

        then: "the y coordinate from the final position should be greater than the y coordinate from the initial position"
            finalPosition.getX() == initialPosition.getX() &&
                        finalPosition.getY() > initialPosition.getY()
    }

    def "car moves left"() {
        given: "a car and its initial position"
            Car car = new Car(new Position(2, 2), '#FFFFFF')
            CarController carController = new CarController(car)
            Position initialPosition = car.getPosition()

        when: "the car moves left"
            Position finalPosition = carController.moveLeft()

        then: "the x coordinate from the final position should be lower than the x coordinate from the initial position"
            finalPosition.getX() < initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }

    def "car moves right"() {
        given: "a car and its initial position"
            Car car = new Car(new Position(2, 2), '#FFFFFF')
            CarController carController = new CarController(car);
            Position initialPosition = car.getPosition()

        when: "the car moves right"
            Position finalPosition = carController.moveRight()

        then: "the x coordinate from the final position should be greater than the x coordinate from the initial position"
            finalPosition.getX() > initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }
}
