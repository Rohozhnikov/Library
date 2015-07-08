package view;

import java.util.List;

import project.dataaccess.DataAccessFacade;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Book;
import model.LibraryMember;
import controller.App;

public class BookChteckoutController {
	// @FXML
	// private TableView<Book> bookTable;
	// @FXML
	// private TableColumn<LibraryMember, String> lastNameColumn;
	// @FXML
	// private TableColumn<LibraryMember, Number> memberIDColumn;

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
	}

	@FXML
	private void initialize() {
		// bookTable.getSelectionModel().selectedItemProperty()
		// .addListener((observable, oldValue, newValue) ->
		// showBookDetails(newValue));

	}

	@FXML
	private void searchChekoutBook() {
		if (!memberIDextField.getText().isEmpty()
				&& !ISBNTextField.getText().isEmpty()) {
			LibraryMember member = new LibraryMember();
			List<LibraryMember> members = DataAccessFacade.readMemberMap();
			// members.forEach(member ->
			// {if(member.getMemberID().equals(memberIDextField.getText())) {n =
			// member.getMemberID();}});
			for (LibraryMember libraryMember : members) {
				if (libraryMember.getMemberID().equals(
						memberIDextField.getText())) {
					member = libraryMember;
				}
			}
			Book book = new Book();
			List<Book> books = DataAccessFacade.readBooks();
			for (Book book2 : books) {
				if (ISBNTextField.getText().equals(book2.getISBN())) {
					book = book2;
				}
			}
		}
	}

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
