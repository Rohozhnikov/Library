package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.LibraryMember;

public class LibraryMemberEditController {

	@FXML
	private TextField firstNameLabel;
	@FXML
	private TextField lastNameLabel;
	@FXML
	private TextField streetLabel;
	@FXML
	private TextField cityLabel;
	@FXML
	private TextField stateLabel;
	@FXML
	private TextField zipLabel;
	@FXML
	private TextField phoneLabel;
	@FXML
	private TextField memberIDLabel;

	private Stage dialogStage;
	private LibraryMember member;
	private Boolean okClicked = false;

	@FXML
	private void initialize() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setMember(LibraryMember member) {
		this.member = member;

		firstNameLabel.setText(member.getFirstName());
		lastNameLabel.setText(member.getLastName());
		phoneLabel.setText(member.getPhone());
		streetLabel.setText(member.getAddress().getStreet());
		stateLabel.setText(member.getAddress().getState());
		cityLabel.setText(member.getAddress().getCity());
		zipLabel.setText(String.valueOf(member.getAddress().getZip()));
		memberIDLabel.setText(String.valueOf(member.getMemberID()));
	}

	@FXML
	private void handleOK() {

		if (isInputValid()) {
			member.setFirstName(firstNameLabel.getText());
			member.setLastName(lastNameLabel.getText());
			member.getAddress().setStreet(streetLabel.getText());
			member.getAddress().setState(stateLabel.getText());
			member.getAddress().setCity(cityLabel.getText());
			member.getAddress().setZip(zipLabel.getText());
			member.setPhone(phoneLabel.getText());
			member.setMemberID(memberIDLabel.getText());

			okClicked = true;
//			File personFile = mainApp.getPersonFilePath();
//			mainApp.savePersonDataToFile(personFile);
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isInputValid() {
		if (firstNameLabel.getText() != null && lastNameLabel.getText() != null && streetLabel.getText() != null
				&& cityLabel.getText() != null && zipLabel.getText() != null && phoneLabel.getText() != null
				&& memberIDLabel.getText() != null && stateLabel.getText() != null) {
			return true;
		}
		return false;
	}

	public Boolean getOkCliced() {
		return okClicked;
	}
}
