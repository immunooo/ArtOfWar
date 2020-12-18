package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import static GUI.GuiStyle.*;

import java.util.ArrayList;

import Capital.Army;
import Events.Dialogue;
import Events.Event;

/**
 * the event window for user
 *
 * @authors <Xiaoyue Zhang>,<Askar Bashirov>
 * @since <pre>Nov 9 2020</pre>
 * @version 1.3.1
 */
public class GuiEventWindow extends Application {
	
	Event event = new Event();
	Army armyS = new Army();
	String[] dialogue = new String[2];
	ArrayList<String> pagesOfDialogue = new ArrayList<String>();
	int pageCount = 0;
	ArrayList<String> choices = new ArrayList<String>();
	int currentPage = 0;
	
	public GuiEventWindow(Event event, Army army)
	{
		this.armyS=army;
		this.event=event;
		this.dialogue = event.getNextDialogue();
		this.choices = event.getChoices();
		int i=0;
		while (!event.DialogueIsEmpty())
		{
			pagesOfDialogue.set(i, event.getNextDialogue()[0]);
			pageCount++;
			i++;
		}
	}
	
	public GuiEventWindow()
	{
		
	}
	
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

    final int[] BUTTON_SHORT_SIZE = new int[]{30,30};

    Button combatButton;
    Button actionButton;

    Button inventoryButton;
    Button mapButton;
    Button buttonShort3;
    Button exitButton;

    Button nextPageButton;
    Button prevPageButton;

    Label eventNarrative;
    
    //inventory opening variable
    boolean inventoryOpen = false;

    //check
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Special Function Test

        // Component Initialize
        eventImage = new ImageView(new Image(eventImageLocation()));

        morale = new ImageView(new Image(moraleImageLocation()));
        army = new ImageView(new Image(armyImageLocation()));
        gold = new ImageView(new Image(goldImageLocation()));
        food  = new ImageView(new Image(foodImageLocation()));

        // Button Long Setting
        final int BUTTON_LONG_SIZE[] = new int[]{120,25};
        
        
        
        
        
        //Askar
        ArrayList<String> choices = event.getChoices();
        //check 12/7
        combatButton= new Button(choices.get(0));
        combatButton.setMaxSize(BUTTON_LONG_SIZE[0],BUTTON_LONG_SIZE[1]);
        combatButton.setMinSize(BUTTON_LONG_SIZE[0],BUTTON_LONG_SIZE[1]);
        buttonSetting(combatButton);

        //also check 12/7
        actionButton = new Button(choices.get(1));
        actionButton.setMaxSize(BUTTON_LONG_SIZE[0],BUTTON_LONG_SIZE[1]);
        actionButton.setMinSize(BUTTON_LONG_SIZE[0],BUTTON_LONG_SIZE[1]);
        buttonSetting(actionButton);

        // Button Short Setting
        inventoryButton = new Button("");
        inventoryButton.setBackground(new Background(new BackgroundImage(new Image("Assets/Icons/Bag.png"),
                null,null,null,null)));
        inventoryButton.setMinSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);
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

        mapButton = new Button("");
        mapButton.setMinSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);
        mapButton.setMaxSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);
        mapButton.setBackground(new Background(new BackgroundImage(new Image("Assets/Icons/Map.png"),
                null,null,null,null)));

        buttonShort3 = new Button("");
        buttonShort3.setMinSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);
        buttonShort3.setMaxSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);

        exitButton = new Button("");
        exitButton.setBackground(new Background(new BackgroundImage(new Image("Assets/Icons/Door.png"),
                null,null,null,null)));
        exitButton.setMinSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);
        exitButton.setMaxSize(BUTTON_SHORT_SIZE[0],BUTTON_SHORT_SIZE[1]);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
            }
        });

        // Page Button Setting
        nextPageButton = new Button();
        nextPageButton.setMaxSize(25,25);
        nextPageButton.setMinSize(25,25);
        nextPageButton.setBackground(new Background(new BackgroundImage(new Image("Assets/Icons/RightArrow.png"),
                null,null,null,null)));
        
        
        
        
        //changed
        nextPageButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent actionEvent) {
        		if (currentPage<pageCount)
        		{
        			currentPage++;
        		}
        		eventNarrative.setText(pagesOfDialogue.get(currentPage));
            }
        });

        prevPageButton = new Button();
        prevPageButton.setMaxSize(25,25);
        prevPageButton.setMinSize(25,25);
        prevPageButton.setBackground(new Background(new BackgroundImage(new Image("Assets/Icons/LeftArrow.png"),
                null,null,null,null)));
        prevPageButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent actionEvent) {
        		if (currentPage>0)
        		{
        			currentPage--;
        		}
        		eventNarrative.setText(pagesOfDialogue.get(currentPage));
            }
        });
        
        
        // Narrative Text Setting
        eventNarrative = new Label();
        
        
        
        
        
        
        //Askar 12/7
        //check with backend
        eventNarrative.setText(this.pagesOfDialogue.get(currentPage));
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

        narrativePane.add(eventNarrative,0,0);
        narrativePane.add(nextPageButton,0,1);
        narrativePane.add(prevPageButton,0,1);
        narrativePane.setMinSize(400,260);
        narrativePane.setMaxSize(400,260);
        narrativePane.setAlignment(Pos.TOP_LEFT);
        narrativePane.setPadding(new Insets(10));
        narrativePane.setStyle(backgroundColor(COLOR.warm_yellow) 
        		+ borderlineSet(2,COLOR.black,TYPE.solid,7));
        narrativePane.setHalignment(nextPageButton, HPos.RIGHT);
        narrativePane.setHalignment(prevPageButton, HPos.LEFT);

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

        buttonLongPane.addColumn(0,combatButton,actionButton);
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
        buttonShortPane.add(mapButton,1,0);
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

 public void buttonSetting(Button button){

     ContextMenu contextMenu = new ContextMenu();
     //need to connect to the back end choices
     MenuItem item1 = new MenuItem(choices.get(0));
     MenuItem item2 = new MenuItem(choices.get(1));
     MenuItem item3 = new MenuItem(choices.get(2));
     contextMenu.getItems().addAll(item1, item2, item3);
     button.setContextMenu(contextMenu);

     button.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent mouseEvent) {
             contextMenu.show(button, Side.LEFT,0,0);
         }
     });
 }
 
 public String moraleImageLocation()
 {
	 //should return the right picture location according to resources
	 return "Assets/Chill.png";
 }
 
 public String armyImageLocation()
 {
	 //need work
	 if (armyS.getSize()<10)
		 return "Assets/Army-Size-0.png";
	 else if (armyS.getSize()<30)
		 return "Assets/Army-Size-1.png";
	 else if (armyS.getSize()<50)
		 return "Assets/Army-Size-2.png";
	 else if (armyS.getSize()<70)
		 return "Assets/Army-Size-3.png";
	 
	 return "Assets/Army-Size-4.png";
 }
 
 public String foodImageLocation()
 {
	 //need work
	 return "Assets/HalfBasket-4.png";
 }
 
 public String goldImageLocation()
 {
	 //need work
	 return "Assets/Half-Full-Chest-4.png";
 }
 
 public String eventImageLocation()
 {
	 //need work
	 return "Assets/Scenery-From-Old-Concept.png";
 }
 
}
