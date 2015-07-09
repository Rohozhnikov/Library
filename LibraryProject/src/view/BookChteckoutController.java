package view;

import java.io.IOException;
import java.util.List;

import project.dataaccess.DataAccessFacade;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Book;
import model.LibraryMember;
import model.Publication;
import controller.App;

public class BookChteckoutController {
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

	private App app;

	public BookChteckoutController() {
	}

	public void setApp(App app) {
		this.app = app;
		bookTable.setItems(DataAccessFacade.readBooks());
	}

	@FXML
	private void initialize() {
		// bookTable.getSelectionModel().selectedItemProperty()
		// .addListener((observable, oldValue, newValue) ->
		// showBookDetails(newValue));
		ISBNColumn.setCellValueFactory(cellData -> cellData.getValue().ISBNProperty());
		MaxCheckoutColumn.setCellValueFactory(cellData -> cellData.getValue().maxCheckoutLengthProperty());
		titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		AvailableColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
//		publicationIDColumn.setCellValueFactory(cellData -> cellData.getValue().publicationIDProperty());
//		AuthorsColumn.setCellValueFactory(cellData -> cellData.getValue().);
		bookTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValuue) -> publicationCheckout(newValuue));

	}

	public void publicationCheckout(Publication publication) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/view/PublicationCheckout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Checkout");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(app.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
//			LibraryMemberEditController controller = loader.getController();
//			controller.setDialogStage(dialogStage);
//			controller.setMember(member);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

//			return controller.getOkCliced();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void searchChekoutBook() {
//		if (!memberIDextField.getText().isEmpty()
//				&& !ISBNTextField.getText().isEmpty()) {
//			LibraryMember member = new LibraryMember();
//			List<LibraryMember> members = DataAccessFacade.readMemberMap();
//			// members.forEach(member ->
//			// {if(member.getMemberID().equals(memberIDextField.getText())) {n =
//			// member.getMemberID();}});
//			for (LibraryMember libraryMember : members) {
//				if (libraryMember.getMemberID().equals(
//						memberIDextField.getText())) {
//					member = libraryMember;
//				}
//			}
			Book book = new Book();
			List<Book> books = DataAccessFacade.readBooks();
			for (Book book2 : books) {
				if (ISBNTextField.getText().equals(book2.getISBN())) {
					book = book2;
				}
			}
		}
//	}

	private void showBookDetails(Book book) {
		if (book != null) {
			ISBNLabel.setText(String.valueOf(book.getISBN()));
			titleLabel.setText(book.getTitle());
			maxCheckoutLabel
					.setText(String.valueOf(book.getMaxCheckoutLength()));
			// authorLabel.setText(book.getAuthors());
			availableLabel.setText(Boolean.toString(book.getAvailable()));
		} else {
			ISBNLabel.setText("");
			titleLabel.setText("");
			maxCheckoutLabel.setText("");
			availableLabel.setText("");
			authorLabel.setText("");
		}
	}

}
