package lms.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lms.business.SystemController;

public class InterfaceController implements Initializable{

	@FXML
	private AnchorPane mainContent;
	@FXML
	private BorderPane mainBorder;
    
	@FXML
    private Label labelUser;
	
    @FXML
    private ImageView imageUser;
    
    @FXML
    private VBox vBoxBoth;
    
	@FXML
	private VBox vBoxAdmin;
	
	@FXML
	private VBox vBoxLibrarian;
	
	@FXML
	private ImageView projectImage;
	
	@FXML
	private ImageView imageLogout;

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		// actiontarget.setText("Sign in button pressed");
		System.out.println("Submit button pressed");
	}

	@FXML
	protected void handleAddMemberButtonAction(ActionEvent event) {
		// actiontarget.setText("Sign in button pressed");

		System.out.println("Member Submit button pressed");
	}

	@FXML
	protected void handleAddMemberSubmitButtonAction(ActionEvent event) {
		// actiontarget.setText("Sign in button pressed");

		// System.out.println(addmember_fname.getText().toString());

		// System.out.println("Member Submit button pressed");
	}
	
	@FXML
    void handleLogoutListener(MouseEvent event) {
		//logout the appliation
		Stage stage = (Stage) mainContent.getScene().getWindow();
		
		SystemController controller = new SystemController();
		controller.currentAuth = null;
		
		//Show the MainWindow
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Login.fxml"));
		Parent loginParent;
		try {
			loginParent = (Parent) fxmlLoader.load();
			Stage mainStage = new Stage();
	        mainStage.setTitle("Login");
	        mainStage.setFullScreen(true);
	        mainStage.setScene(new Scene(loginParent));
	        mainStage.show();
	        
	        stage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

	@FXML
	protected void handleLabelClickListener(MouseEvent event) {
		Parent root;
		String clickedLabel = ((Label) event.getSource()).getId();

		switch (clickedLabel) {
		case "author":
			try {
				mainContent.getChildren().clear();
				root = FXMLLoader.load(getClass()
						.getResource("fxml/Login.fxml"));
				mainContent.getChildren().add(root);
				// mainBorder.setCenter(root);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "book":
			// mainContent.getChildren().clear();
			// Label bookMessage = new Label();
			// HBox bookHBox = new HBox();
			// bookHBox.getChildren().add(bookMessage);
			// mainContent.getChildren().add(bookHBox);
			/*
			 * try { mainContent.getChildren().clear(); root =
			 * FXMLLoader.load(getClass().getResource("fxml/Book.fxml"));
			 * mainContent.getChildren().add(root);
			 * 
			 * } catch (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			// try {
			// mainContent.getChildren().clear();
			// MemberUi memberUi = new MemberUi();
			// Parent root2 =
			// FXMLLoader.load(getClass().getResource("fxml/MemberList.fxml"));
			// mainContent.getChildren().add(root2);
			//
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			try {
				mainContent.getChildren().clear();
				BookUi memberUi = new BookUi();
				root = FXMLLoader.load(getClass().getResource(
						"fxml/BookList.fxml"));
				mainContent.getChildren().add(root);
				
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "member":

			/*
			 * try { mainContent.getChildren().clear(); root =
			 * FXMLLoader.load(getClass().getResource("fxml/Member.fxml"));
			 * mainContent.getChildren().add(root);
			 * 
			 * } catch (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			try {
				mainContent.getChildren().clear();
				MemberUi memberUi = new MemberUi();
				Parent root2 = FXMLLoader.load(getClass().getResource(
						"fxml/MemberList.fxml"));
				mainContent.getChildren().add(root2);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "checkout":
			try {
				mainContent.getChildren().clear();
				MemberUi memberUi = new MemberUi();
				Parent root2 = FXMLLoader.load(getClass().getResource(
						"fxml/CheckoutRecord.fxml"));
				mainContent.getChildren().add(root2);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			break;
		}

		// // actiontarget.setText("Sign in button pressed");
		// System.out.println("Author clicked");

	}

	public AnchorPane getMainContent() {
		return this.mainContent;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image pImage = new Image("/lms/ui/imgs/project.png");
		projectImage.setImage(pImage);
		
		Image userImage;
		
		//set the logout image
		Image logoutImage = new Image("/lms/ui/imgs/logout.png");
		imageLogout.setImage(logoutImage);
		
		SystemController controller = new SystemController();
		System.out.println(controller.currentAuth.toString());

		labelUser.setText(controller.currentAuth.toString());
		//get the image for the user
		switch (controller.currentAuth.toString()) {
		case "ADMIN" :
			userImage = new Image("/lms/ui/imgs/admin.png");
			imageUser.setImage(userImage);
			
			vBoxAdmin.setVisible(true);
			break;
		
		case "LIBRARIAN" :
			userImage = new Image("/lms/ui/imgs/librarian.png");
			imageUser.setImage(userImage);
			vBoxLibrarian.setVisible(true);
			break;
		
		case "BOTH" :
			userImage = new Image("/lms/ui/imgs/both.png");
			imageUser.setImage(userImage);
			vBoxBoth.setVisible(true);
			break;
		
			
		default:
			
			break;
		}
		
	}

}
