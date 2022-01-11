import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import model.Arena
import model.Car
import model.Position
import spock.lang.Specification

class Movement extends Specification {
    def "car is moving when no key is being pressed in the beginning of the game"() {
        given: "an arena and the initial position of the car"
        Arena arena = new Arena(10, 10)
        Position initialPosition = arena.getCarPosition()

        when: "no key is being pressed"
            arena.processKey(new KeyStroke(KeyType.Backspace))

        then: "the car should be always moving to the right"
            arena.getCarPosition().getY() == initialPosition.getY()
    }

    def "car moves up"() {
        given: "a car and its initial position"
            Car car = new Car(2, 2, '-', '#FFFFFF')
            Position initialPosition = car.getPosition()

        when: "the car moves up"
            Position finalPosition = car.moveUp()

        then: "the y coordinate from the final position should be lower than the y coordinate from the initial position"
            finalPosition.getX() == initialPosition.getX() &&
                    finalPosition.getY() < initialPosition.getY()
    }

    def "car moves down"() {
        given: "a car and its initial position"
            Car car = new Car(2, 2, '-', '#FFFFFF')
            Position initialPosition = car.getPosition()

        when: "the car moves down"
            Position finalPosition = car.moveDown()

        then: "the y coordinate from the final position should be greater than the y coordinate from the initial position"
            finalPosition.getX() == initialPosition.getX() &&
                        finalPosition.getY() > initialPosition.getY()
    }

    def "car moves left"() {
        given: "a car and its initial position"
            Car car = new Car(2, 2, '-', '#FFFFFF')
            Position initialPosition = car.getPosition()

        when: "the car moves left"
            Position finalPosition = car.moveLeft()

        then: "the x coordinate from the final position should be lower than the x coordinate from the initial position"
            finalPosition.getX() < initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }

    def "car moves right"() {
        given: "a car and its initial position"
            Car car = new Car(2, 2, '-', '#FFFFFF')
            Position initialPosition = car.getPosition()

        when: "the car moves right"
            Position finalPosition = car.moveRight()

        then: "the x coordinate from the final position should be greater than the x coordinate from the initial position"
            finalPosition.getX() > initialPosition.getX() &&
                finalPosition.getY() == initialPosition.getY()
    }
}
