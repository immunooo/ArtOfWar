package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
 * @authors <Xiaoyue Zhang>,<Askar Bashirov>
 * @since <pre>Nov 9 2020</pre>
 * @version 1.3.1
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

    final int[] BUTTON_LONG_SIZE = new int[]{120,20};
    final int[] BUTTON_SHORT_SIZE = new int[]{30,30};

    Button buttonLong1 = buttonSet(NODE.button,"Fight");
    Button buttonLong2 = buttonSet(NODE.button,"Retreat");
    Button buttonLong3 = buttonSet(NODE.submenu,"Concede", "example text");
    Button buttonLong4 = buttonSet(NODE.submenu,"Betray", "example text");

    Button inventoryButton;
    Button buttonShort2;
    Button buttonShort3;
    Button exitButton;

    Label eventNarrative;
    
    //inventory opening variable
    boolean inventoryOpen = false;

    //check
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Special Function Test

        // Component Initialize
        eventImage = new ImageView(new Image("Assets/Scenery-From-Old-Concept.png"));

        morale = new ImageView(new Image("Assets/Chill.png"));
        army = new ImageView(new Image("Assets/Army-Size-4.png"));
        gold = new ImageView(new Image("Assets/Half-Full-Chest-4.png"));
        food  = new ImageView(new Image("Assets/HalfBasket-4.png"));

        // Button Short Setting
        inventoryButton = new Button("");
        inventoryButton.setBackground(new Background(new BackgroundImage(new Image("Assets/Icons/Bag.png"),
                null,null,null,null)));
        inventoryButton.setMaxSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);
        inventoryButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                if (inventoryOpen)
                {
                    inventoryOpen = false;

                    narrativePane.getChildren().clear();
                    narrativePane.getChildren().add(eventNarrative);
                    narrativePane.setStyle(backgroundColor(COLOR.warm_yellow)
                            + borderlineSet(2,COLOR.black,TYPE.solid,7));
                }
                else
                {
                    inventoryOpen = true;
                    pullInventory();
                }
            }
        });

        buttonShort2 = new Button("");
        buttonShort2.setMinSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);

        buttonShort3 = new Button("");
        buttonShort3.setMinSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);

        exitButton = new Button("");
        exitButton.setBackground(new Background(new BackgroundImage(new Image("Assets/Icons/Door.png"),
                null,null,null,null)));
        exitButton.setMaxSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
            }
        });

        // Narrative Text Setting
        eventNarrative = new Label();
        eventNarrative.setText("Test Text, Long Sentence "
        		+ "Testing ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
        		+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n~\n~\n~\n~\n~\n~\n~\n~\n~\n~\n~\n~\n~");
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
        narrativePane.setStyle(backgroundColor(COLOR.warm_yellow) 
        		+ borderlineSet(2,COLOR.black,TYPE.solid,7));

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
        buttonLongPane.setAlignment(Pos.TOP_CENTER);
        buttonLongPane.setPadding(new Insets(15));
        buttonLongPane.setMaxSize(200,150);
        buttonLongPane.setMinSize(200,150);
        buttonLongPane.setStyle(backgroundColor(COLOR.warm_yellow) 
        		+ borderlineSet(2,COLOR.black,TYPE.solid,7));

        buttonShortBox.getChildren().addAll(buttonShortPane,moralePane);
        buttonShortBox.setAlignment(Pos.CENTER);
        buttonShortBox.setMaxSize(200,100);
        buttonShortBox.setMinSize(200,100);
        buttonShortBox.setStyle(backgroundColor(COLOR.warm_yellow) +
                                borderlineSet(2,COLOR.black,TYPE.solid,7));

        buttonShortPane.setHgap(10);
        buttonShortPane.setVgap(10);
        buttonShortPane.add(inventoryButton,0,0);
        buttonShortPane.add(buttonShort2,1,0);
        buttonShortPane.add(buttonShort3,0,1);
        buttonShortPane.add(exitButton,1,1);
        buttonShortPane.setAlignment(Pos.CENTER);
        buttonShortPane.setMinSize(100,100);
        buttonShortPane.setMaxSize(100,100);

        moralePane.getChildren().add(morale);
        moralePane.setStyle(backgroundColor(COLOR.white) +
                            borderlineSet(2,COLOR.light_gray,TYPE.solid,5));
        moralePane.setAlignment(Pos.CENTER);
        moralePane.setMaxSize(75,75);
        moralePane.setMinSize(75,75);

        resourcePane.addColumn(0,army,gold,food);
        resourcePane.setVgap(10);
        resourcePane.setAlignment(Pos.CENTER);
        resourcePane.setMaxSize(120,260);
        resourcePane.setMinSize(120,260);
        resourcePane.setStyle(  backgroundColor(COLOR.warm_yellow) +
                                borderlineSet(2,COLOR.black,TYPE.solid,7));

        // Scene Initialize
        scene = new Scene(rootPane,800,600);

        // Stage Initialize
        primaryStage.setHeight(640);
        primaryStage.setWidth(800);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
 //method that will change the narrative with the inventory
 public void pullInventory()
 {
	 //variables for different things
	 final int[] SPACE_SIZE = new int[]{66,66};
	 final int[] SPACE_BUTTON_SIZE = new int[] {60,60};
	 final int[] BOTTOM_SIZE = new int[]{396,60};
	 final int[] UP_SIZE = new int[]{400, 198};
	 
	 //grid panels constructors
	 GridPane bottomPane = new GridPane();
	 GridPane buttonPane = new GridPane();
	 GridPane[][] space = new GridPane[6][3];
	 GridPane exitPane = new GridPane();
	 
	 //buttons constructor
	 Button[][] spaceButton = new Button[6][3];
	 Button exitButton = new Button("Back");
	 
	 //preparing narrative pane
	 narrativePane.getChildren().clear();
	 narrativePane.setStyle(backgroundColor(COLOR.brown) +
                            borderlineSet(2,COLOR.light_gray,TYPE.solid,7));
	 
	 //preparing button pane
	 buttonPane.setMaxSize(UP_SIZE[0], UP_SIZE[1]);
	 buttonPane.setMinSize(UP_SIZE[0], UP_SIZE[1]);
	 
	 // making the slots and buttons
	 for (int i=0;i<6;i++)
	 {
		 for (int j=0;j<3;j++)
		 {
			 //buttons
			 spaceButton[i][j] = new Button();
			 spaceButton[i][j].setMaxSize(SPACE_BUTTON_SIZE[0],SPACE_BUTTON_SIZE[1]);
			 spaceButton[i][j].setMinSize(SPACE_BUTTON_SIZE[0],SPACE_BUTTON_SIZE[1]);
			 
			 //slots
			 space[i][j] = new GridPane();
			 space[i][j].setAlignment(Pos.CENTER);
			 space[i][j].add(spaceButton[i][j],0,0);
			 space[i][j].setMaxSize(SPACE_SIZE[0],SPACE_SIZE[1]);
			 space[i][j].setMinSize(SPACE_SIZE[0], SPACE_SIZE[1]);
			 space[i][j].setStyle(backgroundColor(COLOR.white) 
					 + borderlineSet(2,COLOR.black,TYPE.solid,7));
			 
			 //adding to button pane
			 buttonPane.add(space[i][j], i, j);
		 }
	 }
	 
	 //making bottom pane
	 bottomPane.setMaxSize(BOTTOM_SIZE[0], BOTTOM_SIZE[1]);
	 bottomPane.setMinSize(BOTTOM_SIZE[0], BOTTOM_SIZE[1]);
	 bottomPane.setStyle(backgroundColor(COLOR.warm_yellow) 
			 + borderlineSet(2,COLOR.black,TYPE.solid,7));
	 
	 //working with exit button and pane
	 exitButton.setMaxSize(100, 40);
	 exitButton.setMinSize(100, 40);
	 exitButton.setOnAction(new EventHandler<ActionEvent>() {	
     	@Override
         public void handle(ActionEvent actionEvent) 
     	{
         		inventoryOpen = false;
         		
         		narrativePane.getChildren().clear();
         		narrativePane.getChildren().add(eventNarrative);
         	    narrativePane.setStyle(backgroundColor(COLOR.warm_yellow)
         	    		+ borderlineSet(2,COLOR.black,TYPE.solid,7));
        }
     });
	 
	 /*GridPane support = new GridPane();
	 support.setMaxSize(400, 0);
	 support.setMinSize(400, 0);
	 support.setStyle(backgroundColor(COLOR.warm_yellow) 
			 + borderlineSet(2,COLOR.black,TYPE.solid,7));*/
	 
	 exitPane.setMaxSize(100, 40);
	 exitPane.setMinSize(100, 40);
	 exitPane.add(exitButton, 0, 0);
	 exitPane.setAlignment(Pos.CENTER);
	 exitPane.setPadding(new Insets(15));
	 exitPane.setStyle(backgroundColor(COLOR.warm_yellow) 
			 + borderlineSet(2,COLOR.black,TYPE.solid,7));
	 
	 //bottomPane.add(support, 0, 0);
	 bottomPane.setMargin(exitPane,new Insets(8));
	 bottomPane.add(exitPane, 0, 0);
	 bottomPane.setVgap(8);
	 
	 //adding to narrative pane button and bottom panes;
	 narrativePane.add(buttonPane, 0, 0);
	 narrativePane.add(bottomPane, 0, 1);

 }

}
