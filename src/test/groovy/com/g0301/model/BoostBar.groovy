package com.g0301.model

import spock.lang.Specification

class BoostBar extends Specification {

    def "test if boost bar size decreases when using boost"() {
        given: "a car and a boost bar"
            Car car = new Car(new Position(10, 10), "#FFFFFF")
            BoostBar boostBar = new BoostBar()
        when: "the car is using boost"
            car.isUsingBoost()
        then: "the boost bar should decrease"
            boostBar.decrease()
    }

    def "test if boost bar size increases after a while"() {
        given: "a car and a boost bar"
            Car car = new Car(new Position(10, 10), "#FFFFFF")
            BoostBar boostBar = new BoostBar()
        when: "the car doesn't use the boost for a while"
            car.notUsingBoostTime() > 5
        then:
            boostBar.increase()
    }
}
