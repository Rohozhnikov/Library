package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public StringProperty ISBNProperty() {
		return ISBN;
	}

	public BooleanProperty availableProperty() {
		return available;
	}

	public StringProperty authorsProperty() {
		return new SimpleStringProperty(authorsInLine());
	}

	public String authorsInLine() {
		return authors.stream().map(author -> author.getFirstName() + " " + author.getLastName())
				.collect(Collectors.joining(", "));
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

	public boolean isAvailable() {
		if (getCopy() != null) {
			available.set(false);
			return false;
		}
		available.set(true);
		return true;
	}

	@Override
	public String toString() {
		return "\nBook [ISBN=" + ISBN + ", available=" + ", authors=" + authors + ", getTitle()=" + getTitle()
				+ ", getCopy()=" + getCopy() + ", getNextAvailableCopy()=" + nextAvailableCopy()
				+ ", getPublicationID()=" + getPublicationID() + ", getMaxCheckoutLength()=" + getMaxCheckoutLength()
				+ ", getDateDue()=" + getDateDue() + "]\n";
	}

}
