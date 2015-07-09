package view;

import java.io.File;
import java.io.IOException;

import project.dataaccess.DataAccessFacade;
import controller.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuLayoutController {

	private App mainApp;
	private BorderPane rootLayout;

	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleNew() {
//		mainApp.getPersonData().clear();
//		mainApp.setPersonFilePath(null);
	}

	/**
	 * Opens a FileChooser to let the user select an address book to load.
	 */
	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

		if (file != null) {
			DataAccessFacade.readMemberMap();
		}
	}

	/**
	 * Saves the file to the person file that is currently open. If there is no
	 * open file, the "save as" dialog is shown.
	 */
	@FXML
	public void handleSave() {
		File personFile = mainApp.getPersonFilePath();
		if (personFile != null) {
			mainApp.savePersonDataToFile(personFile);
		} else {
			handleSaveAs();
		}
	}

	/**
	 * Opens a FileChooser to let the user select a file to save to.
	 */
	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.savePersonDataToFile(file);
		}
	}

	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AddressApp");
		alert.setHeaderText("About");
		alert.setContentText("Author: Eugene");

		alert.showAndWait();
	}

	@FXML
	private void handleExit() {
		System.exit(0);
	}

	@FXML
	private void handleCheckout() {
		try {
			FXMLLoader loaderMenu = new FXMLLoader();
			loaderMenu.setLocation(App.class.getResource("/view/Menu.fxml"));
			rootLayout = loaderMenu.load();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuLayoutController.class
					.getResource("/view/PublicationOverview.fxml"));
			AnchorPane layout = loader.load();

			rootLayout.setCenter(layout);
			BookChteckoutController bookChteckoutController =loader.getController();
			bookChteckoutController.setApp(mainApp);

			Stage stage = new Stage();
			stage.setTitle("Checkout");
			stage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);

			stage.show();
			new App().setPrimaryStage(stage);
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
			AnchorPane LibraryMemberController = loader.load();

			rootLayout.setCenter(LibraryMemberController);

			Stage stage = new Stage();
			stage.setTitle("Library Member");
			stage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.show();
			
			new App().start(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// @FXML
	// public void displayCheckOutWindow() throws IOException {
	//
	// FXMLLoader loader = new FXMLLoader();
	// loader.setLocation(MenuLayoutController.class
	// .getResource("/view/BookCheckout.fxml"));
	// AnchorPane BookChteckoutController = loader.load();
	// rootLayout.setCenter(BookChteckoutController);
	//
	// }
}