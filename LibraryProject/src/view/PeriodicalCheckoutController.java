package view;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.CheckoutRecordEntry;
import model.LibraryMember;
import model.Periodical;
import util.DateUtil;

public class PeriodicalCheckoutController {

	@FXML
	private Label issueNOLabel;
	@FXML
	private Label TitleLabel;
	@FXML
	private Label MaxCheckoutLabel;
	@FXML
	private Label dateDueLabel;
	@FXML
	private Label numCopiesLabel;

	private Stage dialogStage;
	private Periodical periodical;
	private LibraryMember libraryMember;

	@FXML
	private void initialize() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setPeriodical(Periodical periodical, LibraryMember libraryMember) {
		this.periodical = periodical;
		this.libraryMember = libraryMember;

		issueNOLabel.setText("" + periodical.getIssueNO());
		TitleLabel.setText(periodical.getTitle());
		MaxCheckoutLabel.setText(String.valueOf(periodical.getMaxCheckoutLength()));
		dateDueLabel.setText(DateUtil.format(LocalDate.now().plusDays(periodical.getMaxCheckoutLength())));
		numCopiesLabel.setText(""+periodical.getCopy().size());
	}

	@FXML
	private void handleCheckout() {
		if (periodical.isAvailable()&&libraryMember!=null) {
			libraryMember.addCheckoutEntry(new CheckoutRecordEntry(periodical.nextAvailableCopy(), LocalDate.now(),
					LocalDate.now().plusDays(periodical.getMaxCheckoutLength())));
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Doesn't saved");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Periodical is not available");
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
		periodical.addCopy();
	}

}
