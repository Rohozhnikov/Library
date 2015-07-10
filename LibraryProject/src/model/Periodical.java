package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Periodical extends Publication {

	private IntegerProperty issueNO;
	private BooleanProperty available;

	public Periodical() {
		this(0, null, 0);
	}

	public Periodical(int issueNO, String title, int maxCheckoutLength) {
		super(title, maxCheckoutLength);
		this.issueNO = new SimpleIntegerProperty(issueNO);
		addCopy();
		available = new SimpleBooleanProperty(true);
	}

	public IntegerProperty issueNo() {
		return issueNO;
	}

	public void setIssueNO(int issueNO) {
		this.issueNO.set(issueNO);
	}

	public int getIssueNO() {
		return issueNO.get();
	}

	public boolean getAvailable() {
		return available.get();
	}

	public void setAvailable(boolean available) {
		this.available.set(available);
	}

	public boolean isAvailable() {
		if (getCopy() == null) {
			available.set(false);
			return false;
		}
		available.set(true);
		return true;
	}

	@Override
	public String toString() {
		return "Periodical [issueNO=" + issueNO + ", getTitle()=" + getTitle() + ", getCopy()=" + getCopy()
				+ ", getNextAvailableCopy()=" + nextAvailableCopy() + ", getPublicationID()=" + getPublicationID()
				+ ", getMaxCheckoutLength()=" + getMaxCheckoutLength() + "]";
	}

}
