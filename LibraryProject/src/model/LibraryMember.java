package model;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LibraryMember extends Person {
	private StringProperty memberID;
	private CheckoutRecord record;

	public LibraryMember() {
		this(null, null, null, null, new Address());
	}

	public LibraryMember(String memberID, String firstName, String lastName, String phone, Address address) {
		super(firstName, lastName, phone, address);
		this.memberID = new SimpleStringProperty(memberID);
		record = new CheckoutRecord();
	}

	public String getMemberID() {
		return memberID.get();
	}

	public void setMemberID(String memberID) {
		this.memberID.set(memberID);
	}

	public StringProperty memberIDProperty() {
		return memberID;
	}

	public CheckoutRecord getCheckoutRecord() {
		return record;
	}

	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.record = checkoutRecord;
	}

	public void addCheckoutEntry(CheckoutRecordEntry checkoutRecordEntry) {
		record.addEntry(checkoutRecordEntry);
	}

	public void checkout(Copy copy, LocalDate checkoutDate, LocalDate dueDate) {
		CheckoutRecordEntry entry = new CheckoutRecordEntry(copy, checkoutDate, dueDate);
		record.addEntry(entry);
	}

	@Override
	public String toString() {
		return "\nLibraryMember [memberID=" + memberID + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getPhone()=" + getPhone() + ", getAddress()=" + getAddress() + ", checkoutRecord="
				+ record + "]";
	}

}
