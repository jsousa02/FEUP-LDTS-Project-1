import com.g0301.model.Button
import com.g0301.model.Menu
import com.g0301.model.StartingMenu
import spock.lang.Specification

class MenuTest extends Specification{

    def "Testing Menu Next Button"(){
        given: "a menu with 3 buttons"
        Button TestButton1= new Button();
        Button TestButton2= new Button();
        Button TestButton3= new Button();
        StartingMenu TestMenu= new StartingMenu();
        TestMenu.AddButton(TestButton1)
        TestMenu.AddButton(TestButton2)
        TestMenu.AddButton(TestButton3)
        when: "we are in the third button and we press next button"
        TestMenu.NextButton() //goes to second button
        TestMenu.NextButton() // goes to third button
        TestMenu.NextButton()
        then: "should be in the first button again"
        TestMenu.GetSelectedIndex() == 0;
    }
    def "Testing Menu Previous Button"(){
        given: "a menu with 3 buttons"
        Button TestButton1= new Button();
        Button TestButton2= new Button();
        Button TestButton3= new Button();
        StartingMenu TestMenu= new StartingMenu();
        TestMenu.AddButton(TestButton1)
        TestMenu.AddButton(TestButton2)
        TestMenu.AddButton(TestButton3)
        when: "we are in the third button and we press next button"
        TestMenu.PreviousButton();
        then: "should go to the last button"
        TestMenu.GetSelectedIndex() == 2;
    }
}
