package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import controller.App;

public class MenuLayoutController {

	private BorderPane rootLayout;
	public static Stage primaryStage;

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

//	public void setPrimaryStage(Stage primaryStage) {
//		this.primaryStage = primaryStage;
//	}

	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AddressApp");
		alert.setHeaderText("About");
		alert.setContentText("Author: Eugene");

		alert.showAndWait();
	}

	@FXML
	private void handleCheckout() {
		try {
			FXMLLoader loaderMenu = new FXMLLoader();
			loaderMenu.setLocation(MenuLayoutController.class
					.getResource("/view/Menu.fxml"));
			rootLayout = loaderMenu.load();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuLayoutController.class
					.getResource("/view/CheckoutOverview.fxml"));
			AnchorPane layout = loader.load();

			rootLayout.setCenter(layout);

			Stage stage = new Stage();
			stage.setTitle("Checkout");
			stage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);

			CheckoutController controller = loader.getController();
			controller.setPrimaryStage(stage);
			
			if (primaryStage!=null) {
				primaryStage.hide();
			}
			primaryStage = stage;
			stage.show();
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
			loader.setLocation(MenuLayoutController.class
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
			primaryStage.close();
			primaryStage = stage;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void Publications() {
		try {
			FXMLLoader loaderMenu = new FXMLLoader();
			loaderMenu.setLocation(MenuLayoutController.class
					.getResource("/view/Menu.fxml"));
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
			primaryStage.close();
			primaryStage = stage;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}