package GUI;

import static GUI.GuiStyle.backgroundColor;

import java.util.ArrayList;

import GUI.GuiStyle.COLOR;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Capital.Army;
import Capital.Resources;
import Events.Event;

public class MapAskar extends Application {

    Army army = new Army();
    //Resources res = new Resources();
    MapGraph mapGraph = new MapGraph();
    ArrayList<Event> events = mapGraph.getRandomEvents();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Pane Construction
        GridPane menuPane = new GridPane();
        Button start = new Button("Start");

        start.setMinSize(140,40);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    newEvent(primaryStage);
                }

                catch(Exception e){ System.out.println("Initial Failed");}
            }
        });


        // Pane Setting
        menuPane.add(start,0,1);
        menuPane.setStyle(backgroundColor(COLOR.white));
        menuPane.setMinHeight(200);
        menuPane.setMaxWidth(200);
        menuPane.setAlignment(Pos.CENTER);

        // Scene Setting
        Scene scene = new Scene(menuPane, 800, 600);

        // Stage Setting
        primaryStage.setResizable(false);
        primaryStage.setWidth(800);
        primaryStage.setHeight(640);
        primaryStage.setTitle("The Art of War");

        // Start
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void newEvent(Stage primaryStage) throws Exception
    {
        int i=-1;
        i++;
        Event currentEvent = events.get(i);

        GuiEventWindow eventWindow = new GuiEventWindow(currentEvent, army);
        try {
            eventWindow.start(primaryStage);
        }catch(Exception e){}

    }

}