package view;

import java.io.IOException;
import java.util.Optional;

import controller.App;
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
import model.Book;
import model.LibraryMember;
import model.Periodical;
import model.Publication;
import project.dataaccess.DataAccessFacade;

public class PublicationsController {
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

	ObservableList<Book> books;
	ObservableList<Periodical> periodicals;

	private App app;

	public PublicationsController() {
	}

	public void setApp(App app) {
		this.app = app;
	}

	@FXML
	private void initialize() {
		books = DataAccessFacade.readBooks();
		periodicals = DataAccessFacade.readPeriodicals();

		bookTable.setItems(books);
		periodicalTable.setItems(periodicals);

		ISBNColumn.setCellValueFactory(cellData -> cellData.getValue().ISBNProperty());
		MaxCheckoutColumn.setCellValueFactory(cellData -> cellData.getValue().maxCheckoutLengthProperty());
		titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		AvailableColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
		AuthorsColumn.setCellValueFactory(cellData -> cellData.getValue().authorsProperty());

		IssueNoPeriodicalColumn.setCellValueFactory(cellData -> cellData.getValue().issueNo());
		titlePeriodicalColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		maxCheckoutPeriodicalColumn.setCellValueFactory(cellData -> cellData.getValue().maxCheckoutLengthProperty());

		bookTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValuue) -> showBookDetails(newValuue, null));
		periodicalTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValuue) -> showPeriodicalDetails(newValuue, null));

	}

	private Book getBookByISBN() {
		Optional<Book> enteredBookISBN = books.stream().filter(bk -> bk.getISBN().equals(ISBNTextField.getText()))
				.findAny();
		return enteredBookISBN.get();
	}

	private Periodical getPeriodicalByTitle() {
		Optional<Periodical> enteredPeriodicalInfo = periodicals.stream()
				.filter(periodic -> periodic.getTitle().equals(titleTextField.getText()))
				.filter(periodic -> periodic.getIssueNO() == Integer.valueOf(issueNumberTextField.getText())).findAny();
		return enteredPeriodicalInfo.get();
	}

	@FXML
	private void searchChekoutBook() {
		try {
			if (!ISBNTextField.getText().isEmpty() && titleTextField.getText().isEmpty()
					&& issueNumberTextField.getText().isEmpty()) {
				showBookDetails(getBookByISBN(), null);

			} else if (!titleTextField.getText().isEmpty() && !issueNumberTextField.getText().isEmpty()
					&& ISBNTextField.getText().isEmpty()) {
				showPeriodicalDetails(getPeriodicalByTitle(), null);
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			// alert.setHeaderText("ID is wrong");
			alert.setContentText("Wrong input");
			alert.showAndWait();
		}

	}

	private void showBookDetails(Book book, LibraryMember member) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/view/BookCheckout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("BOOK CHECKOUT");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			BookCheckoutController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setBook(book, member);

			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showPeriodicalDetails(Periodical periodical, LibraryMember member) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("/view/PeriodicalCheckout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Periodical CHECKOUT");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			PeriodicalCheckoutController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPeriodical(periodical, member);

			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
