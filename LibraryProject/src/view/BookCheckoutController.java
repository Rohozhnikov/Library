package view;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Book;
import model.CheckoutRecordEntry;
import model.LibraryMember;

public class BookCheckoutController {

	@FXML
	private Label TitleLabel;
	@FXML
	private Label ISBNLabel;
	@FXML
	private Label MaxCheckoutLabel;
	@FXML
	private Label availableLabel;
	@FXML
	private Label authorsLabel;

	private Stage dialogStage;
	private Book book;
	private LibraryMember libraryMember;

	@FXML
	private void initialize() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setBook(Book book, LibraryMember libraryMember) {
		this.book = book;
		this.libraryMember = libraryMember;

		TitleLabel.setText(book.getTitle());
		ISBNLabel.setText(book.getISBN());
		MaxCheckoutLabel.setText(String.valueOf(book.getMaxCheckoutLength()));
		availableLabel.setText(String.valueOf(book.getAvailable()));
		authorsLabel.setText(book.getAuthorsInLine());
	}

	@FXML
	private void handleCkeckout() {
		libraryMember.addCheckoutEntry(new CheckoutRecordEntry(book
				.getNextAvailableCopy(), LocalDate.now(), LocalDate.now()
				.plusDays(book.getMaxCheckoutLength())));
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

}
