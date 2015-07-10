package model;

import java.util.UUID;

import javax.xml.bind.annotation.XmlTransient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Copy {

	private StringProperty copyID;
	private Publication publication;
	public Copy() {
		this(null);
	}

	public Copy(Publication publication) {
		copyID = new SimpleStringProperty(String.valueOf(UUID.randomUUID()));
		this.publication = publication;
	}


	public String getCopyID() {
		return copyID.get();
	}

	public void setCopyID(String copyID) {
		this.copyID.set(copyID);
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	@Override
	public String toString() {
		return "\n\t\t\t\t\tCopy [copyID=" + copyID + "]";
	}
}
