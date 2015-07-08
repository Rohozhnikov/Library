package view;

import controller.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Author;

public class AuthorOverwievController {
	@FXML
	private TableView<Author> authorTable;
	@FXML
	private TableColumn<Author, String> firstNameColumn;
	@FXML
	private TableColumn<Author, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label stateLabel;
	@FXML
	private Label zipLabel;
	@FXML
	private Label phoneLabel;
	@FXML
	private Label credentialsLabel;

	private App app;

	public AuthorOverwievController() {
	}

	@FXML
	private void initialize() {
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.lastNameProperty());
		showPersonDetails(null);

		authorTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	public void setApp(App app) {
		this.app = app;
//		authorTable.setItems(app.getPersonData());
	}

	private void showPersonDetails(Author person) {
		if (person != null) {
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getAddress().getStreet());
			stateLabel.setText(person.getAddress().getState());
			cityLabel.setText(person.getAddress().getCity());
			zipLabel.setText(String.valueOf(person.getAddress().getZip()));
			phoneLabel.setText(person.getPhone());
			credentialsLabel.setText(person.getCredential());
		} else {
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			stateLabel.setText("");
			cityLabel.setText("");
			zipLabel.setText("");
			phoneLabel.setText("");
			credentialsLabel.setText("");
		}
	}

	@FXML
	private void deleteAuthor() {
		int selectedIndex = authorTable.getSelectionModel().getSelectedIndex();
		authorTable.getItems().remove(selectedIndex);
	}
	@FXML
	private void handleNewPerson() {
//	    Author tempPerson = new Author();
//	    boolean okClicked = app.showPersonEditDialog(tempPerson);
//	    if (okClicked) {
//	        app.getPersonData().add(tempPerson);
//	    }
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditPerson() {
	    Author selectedPerson = authorTable.getSelectionModel().getSelectedItem();
	    if (selectedPerson != null) {
//	        boolean okClicked = app.showPersonEditDialog(selectedPerson);
//	        if (okClicked) {
//	            showPersonDetails(selectedPerson);
//	        }

	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(app.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
	    }
	}

}
