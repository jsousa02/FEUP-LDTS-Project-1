import com.g0301.Game
import com.g0301.gui.LanternaGUI
import com.g0301.model.Button
import com.g0301.model.Position
import com.g0301.state.MenuState
import spock.lang.Specification

class MenuTest extends Specification {

    MenuState menuState = new MenuState(new Game(60, 60, 30), new LanternaGUI(60, 60))

    def "Testing Menu Next Button"() {
        when: "we are in the third button and we press next button"
            menuState.nextButton() //goes to second button
            menuState.nextButton() // goes to third button
            menuState.nextButton()
        then: "should be in the first button again"
            menuState.getSelectedIndex() == 0
    }
    def "Testing Menu Previous Button"(){
        when: "we are in the third button and we press next button"
            menuState.previousButton()
        then: "should go to the last button"
            menuState.getSelectedIndex() == 2
    }
}
