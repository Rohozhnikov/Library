package model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book extends Publication {
	private StringProperty ISBN;
	private BooleanProperty available;
	private List<Author> authors;

	public Book() {
		this(null, null, 0, new ArrayList<Author>());
	}

	public Book(String ISBN, String title, int maxCheckoutLength, List<Author> authors) {
		super(title, maxCheckoutLength);
		this.ISBN = new SimpleStringProperty(ISBN);
		this.authors = authors;
		addCopy();
		available = new SimpleBooleanProperty(true);
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public String getISBN() {
		return ISBN.get();
	}

	public void setISBN(String iSBN) {
		ISBN.set(iSBN);
	}

	public boolean getAvailable() {
		return available.get();
	}

	public void setAvailable(boolean available) {
		this.available.set(available);
	}

	public void isAvailable(boolean b) {
		available.set(b);
	}

	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", available=" + ", authors=" + authors + ", getTitle()=" + getTitle()
				+ ", getCopy()=" + getCopy() + ", getNextAvailableCopy()=" + getNextAvailableCopy()
				+ ", getPublicationID()=" + getPublicationID() + ", getMaxCheckoutLength()=" + getMaxCheckoutLength()
				+ ", getDateDue()=" + "]";
	}

}