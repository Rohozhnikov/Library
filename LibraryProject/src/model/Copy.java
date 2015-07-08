package model;

import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Copy {

	private StringProperty copyID = new SimpleStringProperty(
			String.valueOf(UUID.randomUUID()));

	@Override
	public String toString() {
		return "Copy [copyID=" + copyID + "]";
	}

	public String getCopyID() {
		return copyID.get();
	}

	public void setCopyID(String copyID) {
		this.copyID.set(copyID);
	}
}
