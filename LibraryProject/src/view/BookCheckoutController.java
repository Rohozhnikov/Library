package view;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Book;
import model.CheckoutRecordEntry;
import model.LibraryMember;
import util.DateUtil;

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
	@FXML
	private Label dateDueLabel;
	@FXML
	private Label numCopiesLabel;

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
		dateDueLabel.setText(DateUtil.format(LocalDate.now().plusDays(book.getMaxCheckoutLength())));
		authorsLabel.setText(book.authorsInLine());
		numCopiesLabel.setText(""+book.getCopy().size());
	}

	@FXML
	private void handleCkeckout() {
		if (book.isAvailable()&&libraryMember!=null) {
			libraryMember.addCheckoutEntry(new CheckoutRecordEntry(book.nextAvailableCopy(), LocalDate.now(),
					LocalDate.now().plusDays(book.getMaxCheckoutLength())));
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Doesn't saved");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Book is not available");
			alert.showAndWait();
		}
		dialogStage.close();
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	@FXML
	private void addCopy() {
		book.addCopy();
	}

}
