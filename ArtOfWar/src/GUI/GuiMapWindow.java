package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GuiMapWindow extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		
		
		//Pane construction
		GridPane menu = new GridPane();
		
		menu.setMinSize(800,600);
		//menu.setAlignment(Pos.CENTER);
		
		
		
		//Component Construction
		Button shima = new Button();
		shima.setBackground(new Background(new BackgroundImage(new Image("Assets/Shima.png"),null,null,null,null)));
		shima.setMinSize(40, 40);
		shima.setMaxSize(40, 40);
		
		Button hima = new Button();
		hima.setBackground(new Background(new BackgroundImage(new Image("Assets/Hima.png"),null,null,null,null)));
		hima.setMinSize(40, 40);
		hima.setMaxSize(40, 40);
		
		Button osano = new Button();
		osano.setBackground(new Background(new BackgroundImage(new Image("Assets/Osano.png"),null,null,null,null)));
		osano.setMinSize(40, 40);
		osano.setMaxSize(40, 40);
		
		Button kayama = new Button();
		kayama.setBackground(new Background(new BackgroundImage(new Image("Assets/Kayama.png"),null,null,null,null)));
		kayama.setMinSize(40, 40);
		kayama.setMaxSize(40, 40);
		
		Button kisaki = new Button();
		kisaki.setBackground(new Background(new BackgroundImage(new Image("Assets/Kisaki.png"),null,null,null,null)));
		kisaki.setMinSize(40, 40);
		kisaki.setMaxSize(40, 40);
		
		Button nishi = new Button();
		nishi.setBackground(new Background(new BackgroundImage(new Image("Assets/Nishi.png"),null,null,null,null)));
		nishi.setMinSize(40, 40);
		nishi.setMaxSize(40, 40);
		
		Button kasu = new Button();
		kasu.setBackground(new Background(new BackgroundImage(new Image("Assets/Kasu.png"),null,null,null,null)));
		kasu.setMinSize(40, 40);
		kasu.setMaxSize(40, 40);
		
		Button chinata = new Button();
		chinata.setBackground(new Background(new BackgroundImage(new Image("Assets/Chinata.png"),null,null,null,null)));
		chinata.setMinSize(40, 40);
		chinata.setMaxSize(40, 40);
		
		
		Button hisatsu = new Button();
		hisatsu.setBackground(new Background(new BackgroundImage(new Image("Assets/Hisatsu.png"),null,null,null,null)));
		hisatsu.setMinSize(40, 40);
		hisatsu.setMaxSize(40, 40);
		
		Button kunagi = new Button();
		kunagi.setBackground(new Background(new BackgroundImage(new Image("Assets/Kunagi.png"),null,null,null,null)));
		kunagi.setMinSize(40, 40);
		kunagi.setMaxSize(40, 40);
		
		Button fukamiya = new Button();
		fukamiya.setBackground(new Background(new BackgroundImage(new Image("Assets/Fukamiya.png"),null,null,null,null)));
		fukamiya.setMinSize(40, 40);
		fukamiya.setMaxSize(40, 40);
		
		
		
		
		
		//Scene setting
		Scene  scene = new Scene(menu,800,600);
		
		//Stage Setting
		primaryStage.setResizable(false);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setTitle("Map");
        
        
        GridPane[][] dummyB = new GridPane[50][65];
        for(int i=0; i<50;i++)
        {
        	for(int j=0; j<65;j++)
        	{
        		GridPane b1 = new GridPane();
        		
        		b1.setMaxSize(0, 0);
        		menu.add(b1, i, j);
        		dummyB[i][j]=b1;
        	}
        }
        
        
        //Pane Setting
        menu.setVgap(10);
        menu.setHgap(10);
        
        menu.add(shima,23,14);
        menu.add(hima,26,14);
        menu.add(osano,28,14);
        menu.add(kayama,30,14);
        menu.add(kisaki,25,23);
        menu.add(nishi,27,24);
        menu.add(kasu,28,24);
        menu.add(chinata,24,27);
        menu.add(hisatsu,28,27);
        menu.add(kunagi,29,27);
        menu.add(fukamiya,29,30);
        
        Button prev = new Button("Previous");
        prev.setMaxWidth(100);
        prev.setMinWidth(100);
        prev.maxHeight(30);
        prev.setMinHeight(30);
        menu.add(prev, 37, 34);
        
        
       
        
        
        
        
    
                
        
        //Set background image
        menu.setBackground(new Background(new BackgroundImage(new Image("Assets/final.png"),null,null,null,null)));
        
        //Start
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
	
	
}
