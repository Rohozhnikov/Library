package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import controller.App;

public class WelcomePageController {

	private BorderPane rootLayout;
	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
	private void handleCheckout() {
		try {
			FXMLLoader loaderMenu = new FXMLLoader();
			loaderMenu.setLocation(App.class.getResource("/view/Menu.fxml"));
			rootLayout = loaderMenu.load();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CheckoutController.class
					.getResource("/view/CheckoutOverview.fxml"));
			AnchorPane layout = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Checkout");
			stage.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			rootLayout.setCenter(layout);

			CheckoutController controller = loader.getController();
			controller.setPrimaryStage(stage);
			stage.show();
			primaryStage.hide();
			MenuLayoutController.primaryStage = stage;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleLibraryMembers() {
		try {
			FXMLLoader loaderMenu = new FXMLLoader();
			loaderMenu.setLocation(App.class.getResource("/view/Menu.fxml"));
			rootLayout = loaderMenu.load();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LibraryMemberOverwievController.class
					.getResource("/view/LibraryMemberOverview.fxml"));
			AnchorPane layout = loader.load();
			rootLayout.setCenter(layout);

			Stage stage = new Stage();
			stage.setTitle("Library Member");
			stage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);

			LibraryMemberOverwievController controoller = loader
					.getController();
			controoller.setPrimaryStage(stage);

			stage.show();
			primaryStage.hide();
			MenuLayoutController.primaryStage = stage;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void Publications() {
		try {
			FXMLLoader loaderMenu = new FXMLLoader();
			loaderMenu.setLocation(MenuLayoutController.class.getResource("/view/Menu.fxml"));
			rootLayout = loaderMenu.load();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(PublicationsController.class
					.getResource("/view/Publications.fxml"));
			AnchorPane layout = loader.load();

			rootLayout.setCenter(layout);

			Stage stage = new Stage();
			stage.setTitle("Publications");
			stage.initModality(Modality.WINDOW_MODAL);

			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);

			PublicationsController controller = loader.getController();
			controller.setPrimaryStage(stage);

			stage.show();
			primaryStage.hide();
			MenuLayoutController.primaryStage = stage;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
