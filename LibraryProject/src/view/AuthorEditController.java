package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Author;

public class AuthorEditController {

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
	private TextField credentialsLabel;

	private Stage dialogStage;
	private Author author;
	private Boolean okClicked = false;

	@FXML
	private void initialize() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setAuthor(Author person) {
		this.author = person;
		
		firstNameLabel.setText(person.getFirstName());
		lastNameLabel.setText(person.getLastName());
		phoneLabel.setText(person.getPhone());
		streetLabel.setText(person.getAddress().getStreet());
		stateLabel.setText(person.getAddress().getState());
		cityLabel.setText(person.getAddress().getCity());
		zipLabel.setText(String.valueOf(person.getAddress().getZip()));
		credentialsLabel.setText(person.getCredential());
	}

	@FXML
	private void handleOK() {

		if (isInputValid()) {
			author.setFirstName(firstNameLabel.getText());
			author.setLastName(lastNameLabel.getText());
			author.getAddress().setStreet(streetLabel.getText());
			author.getAddress().setState(stateLabel.getText());
			author.getAddress().setCity(cityLabel.getText());
			author.getAddress().setZip(zipLabel.getText());
			author.setPhone(phoneLabel.getText());
			author.setCredential(credentialsLabel.getText());
			okClicked = true;
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
				&& credentialsLabel.getText() != null && stateLabel.getText() != null) {
			return true;
		}
		return false;
	}

	public Boolean getOkCliced() {
		return okClicked;
	}
}
