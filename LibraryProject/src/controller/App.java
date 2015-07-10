package controller;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import model.LibraryMember;
import model.LibraryMemberWrapper;
import project.dataaccess.DataAccessFacade;
import view.LibraryMemberEditController;
import view.LibraryMemberOverwievController;
import view.MenuLayoutController;

public class App extends Application {
	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = null;
	}

	private BorderPane rootLayout;
	private ObservableList<LibraryMember> memberData = FXCollections
			.observableArrayList();

	public App() {
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("LIBRARY");
		this.primaryStage.getIcons().add(
				new Image("file:resources/images/Closed_Note.png"));

		initRootLayout();
		showLibraryMemberOverwiev();

	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/view/Menu.fxml"));
			rootLayout = loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			MenuLayoutController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

		memberData = DataAccessFacade.readMemberMap();

	}

	public void showLibraryMemberOverwiev() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class
					.getResource("/view/LibraryMemberOverview.fxml"));
			AnchorPane LibraryMemberOverwiew = loader.load();

			rootLayout.setCenter(LibraryMemberOverwiew);

			LibraryMemberOverwievController controller = loader.getController();
			controller.setApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showMemberEditDialog(LibraryMember member) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class
					.getResource("/view/AuthorEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Author");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			LibraryMemberEditController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMember(member);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.getOkCliced();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public File getPersonFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(App.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	// public void setPersonFilePath(File file) {
	// Preferences prefs = Preferences.userNodeForPackage(App.class);
	// if (file != null) {
	// prefs.put("filePath", file.getPath());
	//
	// // Update the stage title.
	// primaryStage.setTitle("AddressApp - " + file.getName());
	// } else {
	// prefs.remove("filePath");
	//
	// // Update the stage title.
	// primaryStage.setTitle("AddressApp");
	// }
	// }

	// public void loadPersonDataFromFile(File file) {
	// try {
	// System.out.println("file = " + file);
	// JAXBContext context =
	// JAXBContext.newInstance(LibraryMemberWrapper.class);
	// Unmarshaller um = context.createUnmarshaller();
	//
	// // Reading XML from the file and unmarshalling.
	// LibraryMemberWrapper wrapper = (LibraryMemberWrapper) um.unmarshal(file);
	//
	// memberData.clear();
	// memberData.addAll(wrapper.getLibraryMembers());
	//
	// // Save the file path to the registry.
	// setPersonFilePath(file);
	//
	// } catch (Exception e) { // catches ANY exception
	// Alert alert = new Alert(AlertType.ERROR);
	// alert.setTitle("Error");
	// alert.setHeaderText("Could not load data");
	// alert.setContentText("Could not load data from file:\n" +
	// file.getPath());
	//
	// alert.showAndWait();
	// }
	// }

	public void savePersonDataToFile(File file) {
		try {
			System.out.println("file = " + file);
			JAXBContext context = JAXBContext
					.newInstance(LibraryMemberWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			LibraryMemberWrapper wrapper = new LibraryMemberWrapper();
			wrapper.setLibraryMembers(memberData);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

			// Save the file path to the registry.
			// setPersonFilePath(file);
		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n"
					+ file.getPath());

			alert.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public ObservableList<LibraryMember> getMemberData() {
		return memberData;
	}
	// public boolean showPersonEditDialog(Author person) {
	// try {
	// // Load the fxml file and create a new stage for the popup dialog.
	// FXMLLoader loader = new FXMLLoader();
	// loader.setLocation(App.class.getResource("/view/AuthorEditDialog.fxml"));
	// AnchorPane page = (AnchorPane) loader.load();
	//
	// // Create the dialog Stage.
	// Stage dialogStage = new Stage();
	// dialogStage.setTitle("Edit Author");
	// dialogStage.initModality(Modality.WINDOW_MODAL);
	// dialogStage.initOwner(primaryStage);
	// Scene scene = new Scene(page);
	// dialogStage.setScene(scene);
	//
	// // Set the person into the controller.
	// AuthorEditController controller = loader.getController();
	// controller.setDialogStage(dialogStage);
	// controller.setAuthor(person);
	//
	// // Show the dialog and wait until the user closes it
	// dialogStage.showAndWait();
	//
	// return controller.getOkCliced();
	// } catch (IOException e) {
	// e.printStackTrace();
	// return false;
	// }
	// }

	// public ObservableList<LibraryMember> getPersonDataFromFile() {
	// File file = getPersonFilePath();
	// try {
	// ObservableList<LibraryMember> newmemberdataList =
	// FXCollections.observableArrayList();
	// JAXBContext context =
	// JAXBContext.newInstance(LibraryMemberWrapper.class);
	// Unmarshaller um = context.createUnmarshaller();
	//
	// // Reading XML from the file and unmarshalling.
	// LibraryMemberWrapper wrapper = (LibraryMemberWrapper) um.unmarshal(file);
	//
	// newmemberdataList.addAll(wrapper.getLibraryMembers());
	//
	// // Save the file path to the registry.
	// setPersonFilePath(file);
	// return newmemberdataList;
	//
	// } catch (Exception e) { // catches ANY exception
	// Alert alert = new Alert(AlertType.ERROR);
	// alert.setTitle("Error");
	// alert.setHeaderText("Could not load data");
	// alert.setContentText("Could not load data from file:\n" +
	// file.getPath());
	//
	// alert.showAndWait();
	// }
	// return memberData;
	// }

	// public void showAuthorOverwiev() {
	// try {
	// FXMLLoader loader = new FXMLLoader();
	// loader.setLocation(App.class.getResource("/view/Author.fxml"));
	// AnchorPane AuthorOverwiew = loader.load();
	//
	// rootLayout.setCenter(AuthorOverwiew);
	//
	// AuthorOverwievController controller = loader.getController();
	// controller.setApp(this);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
}
