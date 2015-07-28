package lms.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lms.business.Person;

public class MemberUi extends Application {
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {

			Parent root = FXMLLoader.load(getClass().getResource(
					"fxml/Memberfx.fxml"));
			primaryStage.setTitle("welcome");
			primaryStage.setScene(new Scene(root, 590, 400));
			primaryStage.show();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// launch(args);
		Application.launch(MemberUi.class, args);
	}

}
