package view;

import java.io.IOException;
import java.time.LocalDate;

import project.dataaccess.DataAccessFacade;
import controller.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CheckoutRecordEntry;
import model.LibraryMember;

public class LibraryMemberOverwievController {
	@FXML
	private TableView<LibraryMember> memberTable;
	@FXML
	private TableColumn<LibraryMember, String> lastNameColumn;
	@FXML
	private TableColumn<LibraryMember, String> FirstNameColumn;
	@FXML
	private TableColumn<LibraryMember, String> memberIDColumn;

	@FXML
	private TableView<CheckoutRecordEntry> checkoutRecordTable;
	@FXML
	private TableColumn<CheckoutRecordEntry, String> copyColumn;
	@FXML
	private TableColumn<CheckoutRecordEntry, LocalDate> CheckoutDateColumn;
	@FXML
	private TableColumn<CheckoutRecordEntry, LocalDate> DueDateColumn;

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

	private ObservableList<LibraryMember> libraryMembers = DataAccessFacade.readMemberMap();
	private Stage primaryStage;
	private LibraryMember libraryMember;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public LibraryMemberOverwievController() {
	}

	@FXML
	private void initialize() {
		memberTable.setItems(libraryMembers);
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		FirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		memberIDColumn.setCellValueFactory(cellData -> cellData.getValue().memberIDProperty());
		showPersonDetails(null);

		memberTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	private void showPersonDetails(LibraryMember member) {
		if (member != null) {
			this.libraryMember = member;
			firstNameLabel.setText(member.getFirstName());
			lastNameLabel.setText(member.getLastName());
			streetLabel.setText(member.getAddress().getStreet());
			stateLabel.setText(member.getAddress().getState());
			cityLabel.setText(member.getAddress().getCity());
			zipLabel.setText(String.valueOf(member.getAddress().getZip()));
			phoneLabel.setText(member.getPhone());
			memberIDLabel.setText(String.valueOf(member.getMemberID()));

			checkoutRecordTable
					.setItems(FXCollections.observableList(member.getCheckoutRecord().getCheckoutRecordEntries()));
			copyColumn.setCellValueFactory(cellData -> cellData.getValue().getCopy().getPublication().titleProperty());
			CheckoutDateColumn.setCellValueFactory(cellData -> cellData.getValue().checkoutDate());
			DueDateColumn.setCellValueFactory(cellData -> cellData.getValue().dueDate());
		} else {
			checkoutRecordTable.setItems(null);

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
		boolean okClicked = showMemberEditDialog(tempMember);
		if (okClicked) {
			libraryMembers.add(tempMember);
		}
	}

	@FXML
	private void handleEditMember() {
		LibraryMember selectedPerson = memberTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = showMemberEditDialog(selectedPerson);
			if (okClicked) {
				showPersonDetails(selectedPerson);
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			// alert.initOwner(prim);
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
			for (LibraryMember libraryMember : libraryMembers) {
				if (id.equals(libraryMember.getMemberID())) {
					if (showMemberEditDialog(libraryMember)) {
						showPersonDetails(libraryMember);
					}
				}
			}
		}

		memberTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	public boolean showMemberEditDialog(LibraryMember member) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/view/AuthorEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Author");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			// dialogStage.initOwner(primaryStage);
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

	@FXML
	private void printToConsoleRecord() {
		if (libraryMember != null) {
			System.out.println(libraryMember);
		}
	}
}
