import spock.lang.Specification

class BaseTest extends Specification {

    def "Testing equals"() {
        given: "a car in a position"
            Car car = new Car(1, 1, " ", '#FFFFFF')

        when: "a wall in the same position and a wall in a different position"
            Wall wallInSamePosition = new Wall(1, 1, "-")
            Wall wallInDifferentPosition = new Wall(1, 2, "-")

        then: "equals must return true if their positions are the same, false otherwise"
            car.getPosition().equals(wallInSamePosition.getPosition())
            car.getPosition().equals(wallInDifferentPosition.getPosition()) == false
    }

    def "Testing the 4 corners of the arena"() {
        given: "walls that are the corners of the arena"
            Wall wall_1 = new Wall(2, 0, "-")
            Wall wall_2 = new Wall(2, 2, "-")
            Wall wall_3 = new Wall(0, 2, "-")
            Wall wall_4 = new Wall(0, 0, "-")

        when: "the arena is created"
            Arena arena = new Arena(3, 3)

        then: "the walls should be created in the arena"
            arena.getWalls().contains(wall_1)
            arena.getWalls().contains(wall_2)
            arena.getWalls().contains(wall_3)
            arena.getWalls().contains(wall_4)
    }
}
