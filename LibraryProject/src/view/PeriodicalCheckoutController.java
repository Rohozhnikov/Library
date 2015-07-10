package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Book;
import model.Periodical;

public class PeriodicalCheckoutController {

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
	private Periodical periodical;

	@FXML
	private void initialize() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setPeriodical(Periodical periodical) {
		this.periodical = periodical;

//		TitleLabel.setText(periodical.getTitle());
//		ISBNLabel.setText(periodical.getISBN());
//		MaxCheckoutLabel.setText(String.valueOf(periodical.getMaxCheckoutLength()));
//		availableLabel.setText(String.valueOf(periodical.getAvailable()));
//		authorsLabel.setText(periodical.getAuthorsInLine());
	}

	@FXML
	private void handleOK() {

	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

}
