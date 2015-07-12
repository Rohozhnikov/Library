package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import controller.App;

public class MenuLayoutController {

	public static BorderPane rootLayout;
	public static Stage primaryStage;

	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AddressApp");
		alert.setHeaderText("About");
		alert.setContentText("Author: Eugene");

		alert.showAndWait();
	}

	@FXML
	private void handlewelcomePage() {
		new App().start(primaryStage);
	}

	@FXML
	private void handleCheckout() {
		try {
			primaryStage.setTitle("Checkout");

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuLayoutController.class
					.getResource("/view/CheckoutOverview.fxml"));
			AnchorPane layout = loader.load();

			rootLayout.setCenter(layout);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleLibraryMembers() {
		try {
			primaryStage.setTitle("Library Member");

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuLayoutController.class
					.getResource("/view/LibraryMemberOverview.fxml"));
			AnchorPane layout = loader.load();
			rootLayout.setCenter(layout);


			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void Publications() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(PublicationsController.class
					.getResource("/view/Publications.fxml"));
			AnchorPane layout = loader.load();

			rootLayout.setCenter(layout);
			primaryStage.setTitle("Publications");

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}