package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty phone;
	private Address address;
	
	public Person() {
		this(null, null, null, null);
	}

	public Person(String firstName, String lastName, String phone,
			Address address) {
		super();
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.phone = new SimpleStringProperty(phone);
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone.set(phone);
	}

}
