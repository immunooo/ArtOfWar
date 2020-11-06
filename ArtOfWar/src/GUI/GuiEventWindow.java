package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.application.Preloader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GuiEventWindow extends Application {
	
	public void start(Stage eventStage) throws Exception
	{
		//Pane setup
		GridPane rootPane = new GridPane();
		GridPane buttonPane = new GridPane();
		
		ImageView mainImage = new ImageView();
		
		mainImage.setImage(new Image("Assets/picture1.png"));
		
		buttonPane.setStyle("-fx-background-color: #D3876F;");
		buttonPane.setMinHeight(300);
		buttonPane.setMaxWidth(800);
		
		rootPane.add(mainImage,0,0);
		rootPane.add(buttonPane,0,1);
		
		// Scene Setting
		Scene scene = new Scene(rootPane, 800, 600);

		// Stage Setting
		eventStage.setWidth(800);
		eventStage.setHeight(600);
		eventStage.setTitle("The Art of War");

		// Start
		eventStage.setScene(scene);
		eventStage.show();
	}
}
