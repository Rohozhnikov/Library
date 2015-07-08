package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Periodical extends Publication {

	private IntegerProperty issueNO;

	public Periodical() {
		this(0, null, 0);
	}

	public Periodical(int issueNO, String title, int maxCheckoutLength) {
		super(title, maxCheckoutLength);
		this.issueNO = new SimpleIntegerProperty(issueNO);
		addCopy();
	}

	public int getIssueNO() {
		return issueNO.get();
	}

	@Override
	public String toString() {
		return "Periodical [issueNO=" + issueNO + ", getTitle()=" + getTitle() + ", getCopy()=" + getCopy()
				+ ", getNextAvailableCopy()=" + getNextAvailableCopy() + ", getPublicationID()=" + getPublicationID()
				+ ", getMaxCheckoutLength()=" + getMaxCheckoutLength() + "]";
	}
}
