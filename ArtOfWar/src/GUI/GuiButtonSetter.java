package GUI;

import javafx.event.EventHandler;

public class GuiButtonSetter {

    enum TYPE{ button, submenu }

    public TYPE type;
    public String text;
    public EventHandler eventHandler = null;

    public GuiButtonSetter(TYPE type, String text){
        this.type = type;
        this.text = text;
    }

    public void typeSetter(TYPE type){
        this.type = type;
    }


}
