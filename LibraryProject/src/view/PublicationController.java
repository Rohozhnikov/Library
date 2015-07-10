package view;

import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Book;
import model.LibraryMember;
import model.Periodical;
import model.Publication;
import project.dataaccess.DataAccessFacade;
import controller.App;

public class PublicationController {
	@FXML
	private TableView<Book> bookTable;
	@FXML
	private TableColumn<Book, String> ISBNColumn;
	@FXML
	private TableColumn<Book, String> publicationIDColumn;
	@FXML
	private TableColumn<Book, Number> MaxCheckoutColumn;
	@FXML
	private TableColumn<Book, String> titleColumn;
	@FXML
	private TableColumn<Book, Boolean> AvailableColumn;
	@FXML
	private TableColumn<Book, String> AuthorsColumn;

	@FXML
	private TableView<Periodical> periodicalTable;
	@FXML
	private TableColumn<Periodical, Number> IssueNoPeriodicalColumn;
	@FXML
	private TableColumn<Periodical, String> titlePeriodicalColumn;
	@FXML
	private TableColumn<Periodical, Number> maxCheckoutPeriodicalColumn;

	@FXML
	private TextField memberIDextField;
	@FXML
	private TextField ISBNTextField;
	@FXML
	private TextField titleTextField;
	@FXML
	private TextField issueNumberTextField;
	@FXML
	private Label ISBNLabel;
	@FXML
	private Label titleLabel;
	@FXML
	private Label maxCheckoutLabel;
	@FXML
	private Label authorLabel;
	@FXML
	private Label availableLabel;
	@FXML
	private Label outputAnswLabel;

	ObservableList<LibraryMember> libraryMembers;
	ObservableList<Book> books;
	ObservableList<Periodical> periodicals;

	private App app;

	public PublicationController() {
	}

	public void setApp(App app) {
		this.app = app;
	}

	@FXML
	private void initialize() {
		libraryMembers = DataAccessFacade.readMemberMap();
		books = DataAccessFacade.readBooks();
		periodicals = DataAccessFacade.readPeriodicals();

		bookTable.setItems(books);
		periodicalTable.setItems(periodicals);

		ISBNColumn.setCellValueFactory(cellData -> cellData.getValue()
				.ISBNProperty());
		MaxCheckoutColumn.setCellValueFactory(cellData -> cellData.getValue()
				.maxCheckoutLengthProperty());
		titleColumn.setCellValueFactory(cellData -> cellData.getValue()
				.titleProperty());
		AvailableColumn.setCellValueFactory(cellData -> cellData.getValue()
				.availableProperty());
		AuthorsColumn.setCellValueFactory(cellData -> cellData.getValue()
				.authorsProperty());

		IssueNoPeriodicalColumn.setCellValueFactory(cellData -> cellData
				.getValue().issueNo());
		titlePeriodicalColumn.setCellValueFactory(cellData -> cellData
				.getValue().titleProperty());
		maxCheckoutPeriodicalColumn.setCellValueFactory(cellData -> cellData
				.getValue().maxCheckoutLengthProperty());

		bookTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValuue) -> publicationCheckout(newValuue));
		periodicalTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValuue) -> publicationCheckout(newValuue));

	}

	public void publicationCheckout(Publication publication) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/view/BookCheckout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Checkout");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(app.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			// LibraryMemberEditController controller = loader.getController();
			// controller.setDialogStage(dialogStage);
			// controller.setMember(member);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			// return controller.getOkCliced();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private LibraryMember getMemberByID() throws Exception {
		Optional<LibraryMember> enteredMemberID = libraryMembers
				.stream()
				.filter(memb -> memb.getMemberID().equals(
						memberIDextField.getText())).findFirst();
		return enteredMemberID.get();

	}

	private Book getBookByISBN() {
		Optional<Book> enteredBookISBN = books.stream()
				.filter(bk -> bk.getISBN().equals(ISBNTextField.getText()))
				.findAny();
		return enteredBookISBN.get();
	}

	private Periodical getPeriodicalByTitle() {
		Optional<Periodical> enteredPeriodicalInfo = periodicals
				.stream()
				.filter(periodic -> periodic.getTitle().equals(
						titleTextField.getText()))
				.filter(periodic -> periodic.getIssueNO() == Integer
						.valueOf(issueNumberTextField.getText())).findAny();
		return enteredPeriodicalInfo.get();
	}

	@FXML
	private void searchChekoutBook() {
		LibraryMember member;
		Book book = null;
		Periodical periodical;
		try {
			member = getMemberByID();
			if (!ISBNTextField.getText().isEmpty()
					&& titleTextField.getText().isEmpty()
					&& issueNumberTextField.getText().isEmpty()) {
				showBookDetails(getBookByISBN(),member);

			} else if (!titleTextField.getText().isEmpty()
					&& !issueNumberTextField.getText().isEmpty()
					&& ISBNTextField.getText().isEmpty()) {
				periodical = getPeriodicalByTitle();
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			// alert.setHeaderText("ID is wrong");
			alert.setContentText("Wrong input");
			alert.showAndWait();
		}

	}

	private void showBookDetails(Book book,LibraryMember member) {

		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/view/BookCheckout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("BOOK CHECKOUT");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			// dialogStage.initOwner();
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			BookCheckoutController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setBook(book, member);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			// return controller.getOkCliced();
		} catch (IOException e) {
			e.printStackTrace();
			// return false;
		}
	}

}
