package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Address {

	private StringProperty street;
	private StringProperty city;
	private StringProperty state;
	private StringProperty zip;

	public Address() {
		this(null, null, null, null);
	}

	public Address(String street, String city, String state, String zip) {
		super();
		this.street = new SimpleStringProperty(street);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zip = new SimpleStringProperty(zip);
	}

	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public String getState() {
		return state.get();
	}


	public void setState(String state) {
		this.state.set(state);
	}

	public String getZip() {
		return zip.get();
	}

	public void setZip(String zip) {
		this.zip.set(zip);
	}

	@Override
	public String toString() {
		return "\n\t\tAddress [street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
	}

}
