package lms.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddMemberUi extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("fxml/AddMemberFormFxml.fxml"));
			primaryStage.setTitle("Add Member");
			primaryStage.setScene(new Scene(root, 590, 400));
			primaryStage.show();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// launch(args);
		Application.launch(AddMemberUi.class, args);
	}

}
