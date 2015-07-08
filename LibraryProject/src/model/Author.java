package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Author extends Person {

	private StringProperty credential;

	public Author(String firstName, String lastName, String phone,
			Address address, String credential) {
		super(firstName, lastName, phone, address);
		this.credential = new SimpleStringProperty(credential);
	}

	public Author() {
		this(null, null, null, new Address(), null);
	}

	public String getCredential() {
		return credential.get();
	}

	public void setCredential(String credential) {
		this.credential.set(credential);
	}

	@Override
	public String toString() {
		return "Author [credential=" + credential + ", getAddress()=" + getAddress() + ", firstNameProperty()="
				+ firstNameProperty() + ", lastNameProperty()=" + lastNameProperty() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", getPhone()=" + getPhone() + "]";
	}

}
