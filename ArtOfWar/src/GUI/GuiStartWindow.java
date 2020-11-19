package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import static GUI.GuiStyle.*;
/**
 * The window for title menu
 *
 * @author <Xiaoyue Zhang>
 * @since <pre>Nov 9 2020</pre>
 * @version 1.2.1
 */
public class GuiStartWindow extends Application{
//check

    int buttonHeight = 35;
    int buttonWidth = 150;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Pane Construction
        GridPane rootPane = new GridPane();
        GridPane menuPane = new GridPane();

        // Component Construction
        ImageView titleImage = new ImageView();

        Button start = new Button("Start");
        Button credit = new Button("Credit");
        Button quit = new Button("Quit");

        // Component Setting
        titleImage.setImage(new Image("Assets/title.jpg"));

        start.setMinSize(buttonWidth,buttonHeight);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GuiEventWindow eventWindow= new GuiEventWindow();
                try {
                    eventWindow.start(primaryStage);
                }
                catch(Exception e){ System.out.println("Initial Failed");}
            }
        });

        credit.setMinSize(buttonWidth,buttonHeight);

        quit.setMinSize(buttonWidth,buttonHeight);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
            }
        });

        // Pane Setting
        menuPane.add(start,1,0);
        menuPane.add(credit,1,1);
        menuPane.add(quit,1,2);
        menuPane.setVgap(20);
        menuPane.setStyle(backgroundColor(COLOR.pink));
        menuPane.setMinHeight(200);
        menuPane.setMaxWidth(200);
        menuPane.setAlignment(Pos.CENTER);

        rootPane.add(titleImage,0,0);
        rootPane.add(menuPane,0,1);
        rootPane.setStyle(backgroundColor(COLOR.red));
        rootPane.setHalignment(menuPane, HPos.CENTER);

        // Scene Setting
        Scene scene = new Scene(rootPane, 800, 600);

        // Stage Setting
        primaryStage.setResizable(false);
        primaryStage.setWidth(800);
        primaryStage.setHeight(640);
        primaryStage.setTitle("The Art of War");

        // Start
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
