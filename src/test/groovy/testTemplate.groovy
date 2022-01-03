import spock.lang.Specification

class testTemplate extends Specification {

    def "one plus one should equal two"() {
        expect:
        1 + 1 == 2
    }
}
