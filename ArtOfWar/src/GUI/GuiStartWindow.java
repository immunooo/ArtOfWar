package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    int buttonHeight = 21;
    int buttonWidth = 121;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Pane Construction
        GridPane rootPane = new GridPane();
        GridPane menuPane = new GridPane();

        // Component Construction
        ImageView titleImage = new ImageView();

        Button start = new Button("");
        Button credit = new Button("");
        Button quit = new Button("");

        // Component Setting
        titleImage.setImage(new Image("Assets/title.jpg"));

        start.setMinSize(buttonWidth,buttonHeight);
        start.setMaxSize(buttonWidth,buttonHeight);
        start.setBackground(new Background(new BackgroundImage(
                new Image("Assets/Icons/Button/Idle/start.png"), null,null,null,null)));
        start.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GuiEventWindow eventWindow = new GuiEventWindow();

                try {
                    eventWindow.start(primaryStage);
                }
                catch(Exception e){ System.out.println("Initial Failed");}
            }
        });
        start.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                start.setBackground(new Background(new BackgroundImage(
                        new Image("Assets/Icons/Button/hoveredOver/start.png"),null,null,null, null)));
            }
        });
        start.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                start.setBackground(new Background(new BackgroundImage(
                        new Image("Assets/Icons/Button/Idle/start.png"),null,null,null,null)));
            }
        });
        start.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                start.setBackground(new Background(new BackgroundImage(
                        new Image("Assets/Icons/Button/Clicked/start.png"),null,null,null,null)));
            }
        });

        credit.setMinSize(buttonWidth,buttonHeight);
        credit.setMaxSize(buttonWidth,buttonHeight);
        credit.setBackground(new Background(new BackgroundImage(
                new Image("Assets/Icons/Button/Idle/credit.png"), null,null,null,null)));
        credit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            }
        });
        credit.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                credit.setBackground(new Background(new BackgroundImage(
                        new Image("Assets/Icons/Button/hoveredOver/credit.png"),null,null,null, null)));
            }
        });
        credit.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                credit.setBackground(new Background(new BackgroundImage(
                        new Image("Assets/Icons/Button/Idle/credit.png"),null,null,null,null)));
            }
        });
        credit.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                credit.setBackground(new Background(new BackgroundImage(
                        new Image("Assets/Icons/Button/Clicked/credit.png"),null,null,null,null)));
            }
        });

        quit.setMinSize(buttonWidth,buttonHeight);
        quit.setMaxSize(buttonWidth,buttonHeight);
        quit.setBackground(new Background(new BackgroundImage(
                new Image("Assets/Icons/Button/Idle/quit.png"), null,null,null,null)));
        quit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.close();
            }
        });
        quit.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                quit.setBackground(new Background(new BackgroundImage(
                        new Image("Assets/Icons/Button/hoveredOver/quit.png"),null,null,null, null)));
            }
        });
        quit.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                quit.setBackground(new Background(new BackgroundImage(
                        new Image("Assets/Icons/Button/Idle/quit.png"),null,null,null,null)));
            }
        });
        quit.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                quit.setBackground(new Background(new BackgroundImage(
                        new Image("Assets/Icons/Button/Clicked/quit.png"),null,null,null,null)));
            }
        });

        // Pane Setting
        menuPane.add(start,1,0);
        menuPane.add(credit,1,1);
        menuPane.add(quit,1,2);
        menuPane.setVgap(30);
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
