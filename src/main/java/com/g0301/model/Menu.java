package com.g0301.model;
import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    private List<Button> buttons;
    private int SelectedIndex = 0;

    public Menu(){
        buttons= new ArrayList<>();
    }
    public List<Button> GetButtons(){
        return buttons;
    }
    public int GetSelectedIndex(){
        return SelectedIndex;
    }
    public void AddButton(Button button) {
        buttons.add(button);
}
    public void NextButton(){
        if (SelectedIndex==(buttons.size()-1)){
            SelectedIndex= 0;
        }
        else SelectedIndex++;
    }
    public void PreviousButton(){
        if (SelectedIndex==0){
            SelectedIndex= (buttons.size()-1);
        }
        else SelectedIndex--;
    }
}