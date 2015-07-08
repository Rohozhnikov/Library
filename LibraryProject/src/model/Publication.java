package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public List<Copy> getCopy() {
		return copy;
	}

	public void setCopy(List<Copy> copy) {
		this.copy = copy;
	}

	public void addCopy() {
		copy.add(new Copy());
	}

	public Copy getNextAvailableCopy() {
		return copy.get(0);
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

}
