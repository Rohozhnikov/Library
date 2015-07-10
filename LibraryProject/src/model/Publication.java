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
	private List<Copy> copy;
	private IntegerProperty maxCheckoutLength;
	private ObjectProperty<LocalDate> dateDue;

	public Publication() {
		this(null, 0);
	}

	public Publication(String title, int maxCheckoutLength) {
		super();
		this.title = new SimpleStringProperty(title);
		this.maxCheckoutLength = new SimpleIntegerProperty(maxCheckoutLength);
		copy = new ArrayList<Copy>();
		publicationID = new SimpleStringProperty(String.valueOf(UUID.randomUUID()));
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
		return copy;
	}

	public void setCopy(List<Copy> copy) {
		this.copy = copy;
	}

	public void addCopy() {
		copy.add(new Copy(this));
	}

	public Copy nextAvailableCopy() {
		Copy c = new Copy(this);
		if (copy == null) {
			return null;
		} else if (copy.size() == 0) {
			c = copy.get(0);
			copy = null;
		} else if (copy.size() > 1) {
			copy.remove(copy);
			c = copy.get(0);
		}
		return c;
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
		return "Publication [publicationID=" + publicationID + ", title=" + title + ", copy=" + copy
				+ ", maxCheckoutLength=" + maxCheckoutLength + ", dateDue=" + dateDue + "]\n";
	}

}
