package view;

import java.time.LocalDate;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.CheckoutRecordEntry;
import model.LibraryMember;
import model.Periodical;
import project.dataaccess.DataAccessFacade;
import util.DateUtil;

public class NewPeriodicalController {

	@FXML
	private TextField issueNOLabel;
	@FXML
	private TextField TitleLabel;
	@FXML
	private TextField MaxCheckoutLabel;
	@FXML
	private TextField numCopiesLabel;

	private Stage dialogStage;
	private List<Periodical> periodicals;
	private LibraryMember libraryMember;

	@FXML
	private void initialize() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void newPeriodical() {
		if (!issueNOLabel.getText().isEmpty() && !TitleLabel.getText().isEmpty()
				&& !MaxCheckoutLabel.getText().isEmpty()) {

			periodicals = DataAccessFacade.readPeriodicals();
			periodicals.add(new Periodical(Integer.valueOf(issueNOLabel.getText()), TitleLabel.getText(),
					Integer.valueOf(MaxCheckoutLabel.getText())));

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
}
