package GUI;
import GUI.GuiEventWindow;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * the class to control all scene and stage change
 *
 * @author <Xiaoyue Zhang>
 * @since <pre>Nov 9 2020</pre>
 * @version 0.8
 */
public class GuiController {

    GuiEventWindow eventWindow;

    public void start(Stage stage){
        eventWindow = new GuiEventWindow();
        try {
            eventWindow.start(stage);
        }catch (Exception e){
            System.out.println("Start Failed");
        }
    }

    public void moraleImageControl(){
        String moraleImage = "morale_1";
        eventWindow.morale.setImage(new Image(moraleImage));
    }

    public String armyImageControl(){
        String armyImage = "army_11";
        return armyImage;
    }

    public String goldImageControl(){
        String goldImage = "gold_11";
        return goldImage;
    }

    public String foodImageControl(){
        String foodImage = "food_11";
        return foodImage;
    }

    public String eventNarrativeControl(){
        String narrative = "";
        return narrative;
    }

    public String eventImageControl(){
        String image = "";
        return image;
    }


}
