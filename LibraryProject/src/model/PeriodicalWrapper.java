package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "periodicals")
public class PeriodicalWrapper {

	private List<Periodical> periodicalList;

	@XmlElement(name = "periodical")
	public List<Periodical> getPeriodicals() {
		return periodicalList;
	}

	public void setPeriodicals(List<Periodical> periodicalList) {
		this.periodicalList = periodicalList;
	}

}
