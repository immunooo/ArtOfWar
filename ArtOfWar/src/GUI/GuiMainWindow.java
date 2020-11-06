package GUI;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class GuiMainWindow extends Application{
//check

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
		start.setMinSize(150,30);
		credit.setMinSize(150,30);
		quit.setMinSize(150,30);

		// Pane Setting
		menuPane.add(start,1,0);
		menuPane.add(credit,1,1);
		menuPane.add(quit,1,2);
		menuPane.setVgap(15);
		menuPane.setStyle("-fx-background-color: #D3876F;");
		menuPane.setMinHeight(160);
		menuPane.setMaxWidth(200);
		menuPane.setAlignment(Pos.CENTER);

		rootPane.add(titleImage,0,0);
		rootPane.add(menuPane,0,1);
		rootPane.setStyle(	"-fx-background-color: #C6584B;");
		rootPane.setHalignment(menuPane, HPos.CENTER);

		// Scene Setting
		Scene scene = new Scene(rootPane, 800, 600);

		// Stage Setting
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.setTitle("The Art of War");

		// Start
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
