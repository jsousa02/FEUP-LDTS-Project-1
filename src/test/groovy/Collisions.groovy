import spock.lang.Specification

class Collisions extends  Specification{
    def "Testing Wall Collision"(){
        given: "an arena and a position where we know there's a wall"
        Arena arena_test = new Arena(5,5)
        Position position_test= new Position(4,0)

        when: "the car moves to the wall position"
        arena_test.moveCar(position_test)

        then: "it should return true because there's a collision"
        arena_test.wall_Collision()
    }

    def "Testing Trail Collision"(){
        given: "an arena"
        Arena arena_test= new Arena(5,5)
        Position position_test1=new Position(2,2)
        Position position_test2=new Position(2,1)

        when: "the car crashes against a trail"
        arena_test.moveCar(position_test1)
        arena_test.getTrailList().add(new Trail(position_test2.getX(),position_test2.getY(),"-"))
        arena_test.moveCar(position_test2)

        then: "a collision should be detected"
        arena_test.trail_Collision()
    }
}
