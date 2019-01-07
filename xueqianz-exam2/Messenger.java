//Xueqian Zhang id:xueqianz
package exam2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Messenger extends Application{
	Message message;  					//this variable must be used to demonstrate the use of polymorphism
	Button[] buttons = new Button[4]; //to be tied to handlers 
	
	
	TextField inputMessageTextField = new TextField();  //will take user input
	TextField inputKeyTextField = new TextField();  //will take user input
	Text resultLabel = new Text("Enter a message, a key, and pick Encrypt / Decrypt algorithm");  //will display the result

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(setupScene(), 600, 200);
		primaryStage.setTitle("Messenger");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	BorderPane setupScene() {
		//create various GUI components
		BorderPane root = new BorderPane();

		String[] options = {"Caesar Encrypt", "Caesar Decrypt", "Keyword Encrypt", "Keyword Decrypt"}; //text to display on buttons
		GridPane seriesGrid = new GridPane();	
		GridPane inputGrid = new GridPane();
		Label inputLabel1 = new Label("Enter message");
		Label inputLabel2 = new Label("Enter key");
		
		//borders and colors
		inputGrid.setStyle("-fx-border-color: lightgrey");
		seriesGrid.setStyle("-fx-border-color: lightgrey");
		root.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		inputGrid.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
		//fonts
		inputLabel1.setFont(Font.font(15));
		inputLabel2.setFont(Font.font(15));
		inputMessageTextField.setFont(Font.font(15));
		inputKeyTextField.setFont(Font.font(15));
		resultLabel.setFont(Font.font(15));
		
		//prompts
		inputMessageTextField.setPromptText("Enter message");
		inputKeyTextField.setPromptText("Enter encrypt / decrypt key");
		
		//sizes
		inputGrid.setMaxHeight(100);
		inputLabel1.setPrefSize(150, 50);
		inputLabel2.setPrefSize(150, 50);
		inputMessageTextField.setPrefSize(450, 50);
		inputKeyTextField.setPrefSize(450, 50);
		
		//alignment
		inputMessageTextField.setAlignment(Pos.CENTER);
		inputKeyTextField.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(resultLabel, Pos.CENTER);

		//add to grid
		inputGrid.add(inputLabel1, 0, 0);
		inputGrid.add(inputMessageTextField, 1, 0, 2, 1);
		inputGrid.add(inputLabel2, 0, 1);
		inputGrid.add(inputKeyTextField, 1, 1, 2, 1);
		
		//setup the buttons
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button(options[i]);
			buttons[i].setPrefSize(175, 50);
			buttons[i].setFont(Font.font(15));
			seriesGrid.add(buttons[i],  i, 0);
		}	
		
		//set up root
		root.setTop(seriesGrid);	
		root.setCenter(inputGrid);
		root.setBottom(resultLabel);
		
		buttons[0].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				message = new Caesar(inputMessageTextField.getText(), inputKeyTextField.getText());
				if(message.validateInput()) {
					resultLabel.setText("Caesar Encryption: " + message.encrypt());
				}
				else {
					resultLabel.setText("Caesar Encryption: Encryption failed");
				}
				
			}
		});
		
		buttons[1].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				message = new Caesar(inputMessageTextField.getText(), inputKeyTextField.getText());
				if(message.validateInput()) {
					resultLabel.setText("Caesar Decryption: " + message.decrypt());
				}else {
					resultLabel.setText("Caesar Decryption: Decryption failed");
				}
			}
		});
		
		buttons[2].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				message = new Keyword(inputMessageTextField.getText(), inputKeyTextField.getText());
				if(message.validateInput()) {
					resultLabel.setText("Keyword Encryption: " + message.encrypt());
				}else {
					resultLabel.setText("Keyword Encryption: Encryption failed");
				}
			}
		});
		
		buttons[3].setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				message = new Keyword(inputMessageTextField.getText(), inputKeyTextField.getText());
				if(message.validateInput()) {
					resultLabel.setText("Keyword Decryption: " + message.decrypt());
				}else {
					resultLabel.setText("Keyword Decryption: Decryption failed");
				}
				
			}
		});
		
		return root;
	}
	
	

}
