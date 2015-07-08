package model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import util.LocalDateAdapter;

public class CheckoutRecordEntry {

	private ObjectProperty<LocalDate> checkoutDate;
	private ObjectProperty<LocalDate> dueDate;
	private Copy copy;

	public CheckoutRecordEntry() {
		this(new Copy(), null, null);
	}

	public CheckoutRecordEntry(Copy copy, LocalDate checkoutDate,
			LocalDate dueDate) {
		this.checkoutDate = new SimpleObjectProperty<LocalDate>(checkoutDate);
		this.dueDate = new SimpleObjectProperty<LocalDate>(dueDate);
		this.copy = copy;
	}

	@Override
	public String toString() {
		return "CheckoutRecordEntry [checkoutDate=" + checkoutDate
				+ ", dueDate=" + dueDate + ", copy=" + "]";
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getCheckoutDate() {
		return checkoutDate.get();
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getDueDate() {
		return dueDate.get();
	}

	public Copy getCopy() {
		return copy;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate.set(checkoutDate);
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate.set(dueDate);
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}
}
