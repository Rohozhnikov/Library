package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "books")
public class BookWrapper {

	private List<Book> booksLibrary;

	@XmlElement(name = "book")
	public List<Book> getBooks() {
		return booksLibrary;
	}

	public void setBooks(List<Book> booksLibrary) {
		this.booksLibrary = booksLibrary;
	}

}
