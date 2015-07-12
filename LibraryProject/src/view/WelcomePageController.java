package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WelcomePageController {

	private BorderPane rootLayout;
	private Stage primaryStage;

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
	private void handleCheckout() {
		try {
			primaryStage.setTitle("Checkout");
			primaryStage.show();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(WelcomePageController.class
					.getResource("/view/CheckoutOverview.fxml"));
			AnchorPane layout = loader.load();

			rootLayout.setCenter(layout);

			MenuLayoutController.primaryStage = primaryStage;
			MenuLayoutController.rootLayout = rootLayout;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleLibraryMembers() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LibraryMemberOverwievController.class
					.getResource("/view/LibraryMemberOverview.fxml"));
			AnchorPane layout = loader.load();
			rootLayout.setCenter(layout);

			primaryStage.setTitle("Library Member");
			primaryStage.show();
			MenuLayoutController.primaryStage = primaryStage;
			MenuLayoutController.rootLayout = rootLayout;
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
			MenuLayoutController.primaryStage = primaryStage;
			MenuLayoutController.rootLayout = rootLayout;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
