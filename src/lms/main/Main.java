package lms.main;

import javafx.application.Application;
import lms.ui.MainContainer;
import lms.ui.LoginForm;

public class Main {
	public static void main(String[] args) {
		Application.launch(LoginForm.class);
//		Application.launch(LoginForm.class);
		Application.launch(MainContainer.class);
	}
}