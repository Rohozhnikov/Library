package view;

import controller.App;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.LibraryMember;

public class LibraryMemberOverwievController {
	@FXML
	private TableView<LibraryMember> memberTable;
	@FXML
	private TableColumn<LibraryMember, String> lastNameColumn;
	@FXML
	private TableColumn<LibraryMember, String> memberIDColumn;

	@FXML
	private Label memberIDLabel;
	@FXML
	private Label memberIDSearchLabel;
	@FXML
	private TextField memberIDSearchTextField;
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

	private App app;

	public LibraryMemberOverwievController() {
	}

	@FXML
	private void initialize() {
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		memberIDColumn.setCellValueFactory(cellData -> cellData.getValue().memberIDProperty());
		showPersonDetails(null);

		memberTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	public void setApp(App app) {
		this.app = app;
		memberTable.setItems(app.getMemberData());
	}

	private void showPersonDetails(LibraryMember member) {
		if (member != null) {
			firstNameLabel.setText(member.getFirstName());
			lastNameLabel.setText(member.getLastName());
			streetLabel.setText(member.getAddress().getStreet());
			stateLabel.setText(member.getAddress().getState());
			cityLabel.setText(member.getAddress().getCity());
			zipLabel.setText(String.valueOf(member.getAddress().getZip()));
			phoneLabel.setText(member.getPhone());
			memberIDLabel.setText(String.valueOf(member.getMemberID()));
		} else {
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			stateLabel.setText("");
			cityLabel.setText("");
			zipLabel.setText("");
			phoneLabel.setText("");
			memberIDLabel.setText("");
		}
	}

	@FXML
	private void deleteLibraryMember() {
		int selectedIndex = memberTable.getSelectionModel().getSelectedIndex();
		memberTable.getItems().remove(selectedIndex);
	}

	@FXML
	private void handleNewMember() {
		LibraryMember tempMember = new LibraryMember();
		boolean okClicked = app.showMemberEditDialog(tempMember);
		if (okClicked) {
			app.getMemberData().add(tempMember);
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditMember() {
		LibraryMember selectedPerson = memberTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = app.showMemberEditDialog(selectedPerson);
			if (okClicked) {
				showPersonDetails(selectedPerson);
			}

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

	@FXML
	private void searchByID() {
		if (memberIDSearchTextField.getText().isEmpty()) {

			memberTable.getSelectionModel().selectedItemProperty()
					.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
		} else {
			String id = memberIDSearchTextField.getText();
			memberIDSearchTextField.setText("");
			ObservableList<LibraryMember> libraryMembers = app.getMemberData();
			for (LibraryMember libraryMember : libraryMembers) {
				if (id.equals(libraryMember.getMemberID())) {
					if (app.showMemberEditDialog(libraryMember)) {
						showPersonDetails(libraryMember);
					}
				}
			}
		}

		memberTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

}
