package GUI;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import static GUI.GuiStyle.*;

/**
 * the event window for user
 *
 * @author <Xiaoyue Zhang>
 * @since <pre>Nov 9 2020</pre>
 * @version 1.2
 */
public class GuiEventWindow extends Application {
    /** Scene Construction */
    Scene scene;

    /** Pane Construction */
    GridPane rootPane = new GridPane();
    GridPane controlPane = new GridPane();
    GridPane buttonLongPane = new GridPane();
    GridPane buttonShortPane = new GridPane();
    GridPane resourcePane = new GridPane();
    GridPane moralePane = new GridPane();
    GridPane narrativePane = new GridPane();
    HBox buttonShortBox = new HBox();

    /** Component Construction */
    ImageView eventImage;

    ImageView morale;
    ImageView army ;
    ImageView gold;
    ImageView food;

    ImageView buttonLong;
    ImageView buttonShort;

    final int[] BUTTON_LONG_SIZE = new int[]{120,20};
    final int[] BUTTON_SHORT_SIZE = new int[]{30,30};

    Button buttonLong1;
    Button buttonLong2;
    Button buttonLong3;
    Button buttonLong4;

    Button buttonShort1;
    Button buttonShort2;
    Button buttonShort3;
    Button buttonShort4;

    Label eventNarrative;

    //check
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Component Initialize
        eventImage = new ImageView(new Image("Assets/eventImage_1.jpg"));

        morale = new ImageView(new Image("Assets/StatusBars/Morale/morale_0.png"));
        army = new ImageView(new Image("Assets/StatusBars/Army/Army_1/Army-Size-4.png"));
        gold = new ImageView(new Image("Assets/StatusBars/Army/Army_1/Army-Size-4.png"));
        food  = new ImageView(new Image("Assets/StatusBars/Army/Army_1/Army-Size-4.png"));

        //buttonLong = new ImageView(new Image("Assets/GUI/Button_Long.png"));
        //buttonShort = new ImageView(new Image("Assets/GUI/Button_Short.png"));

        buttonLong1 = new Button("");
        buttonLong2 = new Button("");
        buttonLong3 = new Button("");
        buttonLong4 = new Button("");
        buttonLong1.setMinSize(BUTTON_LONG_SIZE[0],BUTTON_LONG_SIZE[1]);
        buttonLong2.setMinSize(BUTTON_LONG_SIZE[0],BUTTON_LONG_SIZE[1]);
        buttonLong3.setMinSize(BUTTON_LONG_SIZE[0],BUTTON_LONG_SIZE[1]);
        buttonLong4.setMinSize(BUTTON_LONG_SIZE[0],BUTTON_LONG_SIZE[1]);

        buttonShort1 = new Button("");
        buttonShort2 = new Button("");
        buttonShort3 = new Button("");
        buttonShort4 = new Button("");
        buttonShort1.setMinSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);
        buttonShort2.setMinSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);
        buttonShort3.setMinSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);
        buttonShort4.setMinSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);

        eventNarrative = new Label();
        eventNarrative.setText("Test Text, Long Sentence Testing ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n~\n~\n~\n~\n~\n~\n~\n~\n~\n~\n~\n~\n~");
        eventNarrative.setFont(Font.font("Verdana",12));
        eventNarrative.setTextFill(Color.BLACK);
        eventNarrative.setWrapText(true);
        eventNarrative.setTextAlignment(TextAlignment.LEFT);

        // Pane Initialize
        rootPane.add(eventImage,0,0);
        rootPane.add(narrativePane,0,1);
        rootPane.setHalignment(narrativePane, HPos.LEFT);
        rootPane.setMargin(narrativePane,new Insets(20));
        rootPane.add(controlPane,0,1);
        rootPane.setHalignment(controlPane, HPos.RIGHT);
        rootPane.setMargin(controlPane,new Insets(20));
        rootPane.setStyle(backgroundColor(COLOR.brown));

        narrativePane.getChildren().add(eventNarrative);
        narrativePane.setMinSize(400,260);
        narrativePane.setMaxSize(400,260);
        narrativePane.setAlignment(Pos.TOP_LEFT);
        narrativePane.setMargin(eventNarrative,new Insets(10));
        narrativePane.setStyle(backgroundColor(COLOR.warm_yellow) + borderlineSet(2,COLOR.black,TYPE.solid,7));

        controlPane.add(buttonLongPane,0,0);
        controlPane.setValignment(buttonLongPane, VPos.TOP);
        controlPane.add(buttonShortBox,0,0);
        controlPane.setValignment(buttonShortBox, VPos.BOTTOM);
        controlPane.add(resourcePane,1,0);
        controlPane.setHgap(10);
        controlPane.setAlignment(Pos.CENTER_LEFT);
        controlPane.setMaxSize(350,260);
        controlPane.setMinSize(350,260);
        //controlPane.setStyle(borderlineSet(1,COLOR.black,TYPE.solid));

        buttonLongPane.addColumn(0,buttonLong1,buttonLong2,buttonLong3,buttonLong4);
        buttonLongPane.setVgap(12);
        buttonLongPane.setAlignment(Pos.CENTER);
        buttonLongPane.setMaxSize(200,150);
        buttonLongPane.setMinSize(200,150);
        buttonLongPane.setStyle(backgroundColor(COLOR.warm_yellow) + borderlineSet(2,COLOR.black,TYPE.solid,7));

        buttonShortBox.getChildren().addAll(buttonShortPane,moralePane);
        buttonShortBox.setAlignment(Pos.CENTER);
        buttonShortBox.setMaxSize(200,100);
        buttonShortBox.setMinSize(200,100);
        buttonShortBox.setStyle(backgroundColor(COLOR.warm_yellow) + borderlineSet(2,COLOR.black,TYPE.solid,7));

        buttonShortPane.setHgap(10);
        buttonShortPane.setVgap(10);
        buttonShortPane.add(buttonShort1,0,0);
        buttonShortPane.add(buttonShort2,1,0);
        buttonShortPane.add(buttonShort3,0,1);
        buttonShortPane.add(buttonShort4,1,1);
        buttonShortPane.setAlignment(Pos.CENTER);
        buttonShortPane.setMinSize(100,100);
        buttonShortPane.setMaxSize(100,100);

        moralePane.getChildren().add(morale);
        moralePane.setStyle(backgroundColor(COLOR.white) + borderlineSet(2,COLOR.black,TYPE.solid,5));
        moralePane.setAlignment(Pos.CENTER);
        moralePane.setMaxSize(75,75);
        moralePane.setMinSize(75,75);

        resourcePane.addColumn(0,army,gold,food);
        resourcePane.setVgap(10);
        resourcePane.setAlignment(Pos.CENTER);
        resourcePane.setMaxSize(120,260);
        resourcePane.setMinSize(120,260);
        resourcePane.setStyle(backgroundColor(COLOR.warm_yellow) + borderlineSet(2,COLOR.black,TYPE.solid,7));

        // Scene Initialize
        scene = new Scene(rootPane,800,600);

        // Stage Initialize
        primaryStage.setHeight(640);
        primaryStage.setWidth(800);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
