package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "members")
public class LibraryMemberWrapper {

	private List<LibraryMember> libraryMembers;

	@XmlElement(name = "member")
	public List<LibraryMember> getLibraryMembers() {
		return libraryMembers;
	}

	public void setLibraryMembers(List<LibraryMember> libraryMembers) {
		this.libraryMembers = libraryMembers;
	}
}
