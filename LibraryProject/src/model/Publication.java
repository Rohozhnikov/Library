package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.LocalDateAdapter;

public class Publication {

	private StringProperty publicationID;
	private StringProperty title;
	private List<Copy> copies;
	private IntegerProperty maxCheckoutLength;
	private ObjectProperty<LocalDate> dateDue;

	public Publication() {
		this(null, 0);
	}

	public Publication(String title, int maxCheckoutLength) {
		super();
		this.title = new SimpleStringProperty(title);
		this.maxCheckoutLength = new SimpleIntegerProperty(maxCheckoutLength);
		copies = new ArrayList<Copy>();
		publicationID = new SimpleStringProperty(String.valueOf(UUID
				.randomUUID()));
		dateDue = new SimpleObjectProperty<LocalDate>(LocalDate.now());
	}

	public StringProperty titleProperty() {
		return title;
	}

	public StringProperty publicationIDProperty() {
		return publicationID;
	}

	public IntegerProperty maxCheckoutLengthProperty() {
		return maxCheckoutLength;
	}

	public ObjectProperty<LocalDate> dateDueProperty() {
		return dateDue;
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	@XmlTransient
	public List<Copy> getCopy() {
		return copies;
	}

	public void setCopy(List<Copy> copy) {
		this.copies = copy;
	}

	public void addCopy() {
		copies.add(new Copy(this));
	}

	public Copy nextAvailableCopy() {

		if (copies == null || copies.size() == 0) {
			return null;
		}
		Copy copy = copies.get(0);
		copies.remove(copies.get(0));
		return copy;

		// Copy c = new Copy(this);
		// if (copies == null) {
		// return null;
		// } else if (copies.size() == 0) {
		// c = copies.get(0);
		// copies = null;
		// } else if (copies.size() > 1) {
		// copies.remove(copies);
		// c = copies.get(0);
		// }
		// return c;
	}

	public String getPublicationID() {
		return publicationID.get();
	}

	public void setPublicationID(String publicationID) {
		this.publicationID.set(publicationID);
	}

	public int getMaxCheckoutLength() {
		return maxCheckoutLength.get();
	}

	public void setMaxCheckoutLength(int maxCheckoutLength) {
		this.maxCheckoutLength.set(maxCheckoutLength);
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getDateDue() {
		return dateDue.get();
	}

	public void setDateDue(LocalDate dateDue) {
		this.dateDue.set(dateDue);
	}

	@Override
	public String toString() {
		return "Publication [publicationID=" + publicationID + ", title="
				+ title + ", copy=" + copies + ", maxCheckoutLength="
				+ maxCheckoutLength + ", dateDue=" + dateDue + "]\n";
	}

}
