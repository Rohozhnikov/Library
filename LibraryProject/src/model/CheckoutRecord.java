package model;

import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord {

	private List<CheckoutRecordEntry> checkoutRecordEntries = new ArrayList<>();
	// private LibraryMember libraryMember;

	public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
		return checkoutRecordEntries;
	}

	public void setCheckoutRecordEntries(List<CheckoutRecordEntry> checkoutRecordEntries) {
		this.checkoutRecordEntries = checkoutRecordEntries;
	}

	public void addEntry(CheckoutRecordEntry checkoutRecordEntry) {
		checkoutRecordEntries.add(checkoutRecordEntry);
	}

	@Override
	public String toString() {
		return "\n\tCheckoutRecord [checkoutRecordEntries=" + checkoutRecordEntries + ", libraryMember=" + "]";
	}

}
