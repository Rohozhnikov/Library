package project.dataaccess;

import java.io.File;
import java.util.List;
import java.util.prefs.Preferences;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.Book;
import model.BookWrapper;
import model.LibraryMember;
import model.LibraryMemberWrapper;
import model.Periodical;
import model.PeriodicalWrapper;
import controller.App;

public class DataAccessFacade {
	private static Stage primaryStage;

	private static ObservableList<LibraryMember> memberData = FXCollections
			.observableArrayList();
	private static ObservableList<Periodical> periodicalData = FXCollections
			.observableArrayList();
	private static ObservableList<Book> bookData = FXCollections
			.observableArrayList();

	public static void loadBookMap(List<Book> books) {
		File file = new File("./resources/dataStorage/BOOKS.xml");
		try {
			JAXBContext context = JAXBContext.newInstance(BookWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			BookWrapper wrapper = new BookWrapper();
			wrapper.setBooks(books);
			m.marshal(wrapper, file);

		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n"
					+ file.getPath());

			alert.showAndWait();
		}
	}

	public static void loadPeriodicalsMap(List<Periodical> periodicals) {
		File file = new File("./resources/dataStorage/PERIODICALS.xml");
		try {
			JAXBContext context = JAXBContext
					.newInstance(PeriodicalWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			PeriodicalWrapper wrapper = new PeriodicalWrapper();
			wrapper.setPeriodicals(periodicals);
			m.marshal(wrapper, file);
		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n"
					+ file.getPath());

			alert.showAndWait();
		}
	}

	public static void loadMemberMap(List<LibraryMember> members) {
		File file = new File("./resources/dataStorage/MEMBERS.xml");
		try {
			JAXBContext context = JAXBContext
					.newInstance(LibraryMemberWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			LibraryMemberWrapper wrapper = new LibraryMemberWrapper();
			wrapper.setLibraryMembers(members);
			m.marshal(wrapper, file);
		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n"
					+ file.getPath());

			alert.showAndWait();
		}

	}

	public static void setPersonFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(App.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title.
			primaryStage.setTitle("AddressApp - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title.
			primaryStage.setTitle("AddressApp");
		}
	}

	public static ObservableList<Book> readBooks() {
		if (bookData.size() != 0) {
			return bookData;
		}
		File file = new File("./resources/dataStorage/BOOKS.xml");
		try {
			JAXBContext context = JAXBContext.newInstance(BookWrapper.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			BookWrapper wrapper = (BookWrapper) unmarshaller.unmarshal(file);

			bookData.clear();
			bookData.addAll(wrapper.getBooks());
			// bookData.forEach(b -> books.put(b.getISBN(), b));

		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not load data");
			alert.setContentText("Could not load data from file:\n"
					+ file.getPath());

			alert.showAndWait();
		}
		return bookData;
	}

	public static ObservableList<Periodical> readPeriodicals() {
		if (periodicalData.size() != 0) {
			return periodicalData;
		}
		File file = new File("./resources/dataStorage/PERIODICALS.xml");
		try {
			JAXBContext context = JAXBContext
					.newInstance(PeriodicalWrapper.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			PeriodicalWrapper wrapper = (PeriodicalWrapper) unmarshaller
					.unmarshal(file);

			periodicalData.clear();
			periodicalData.addAll(wrapper.getPeriodicals());

		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not load data");
			alert.setContentText("Could not load data from file:\n"
					+ file.getPath());

			alert.showAndWait();
		}
		return periodicalData;
	}

	public static ObservableList<LibraryMember> readMemberMap() {
		if (memberData.size() != 0) {
			return memberData;
		}
		File file = new File("./resources/dataStorage/MEMBERS.xml");
		try {
			JAXBContext context = JAXBContext
					.newInstance(LibraryMemberWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			LibraryMemberWrapper wrapper = (LibraryMemberWrapper) um
					.unmarshal(file);

			memberData.clear();
			memberData.addAll(wrapper.getLibraryMembers());
		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not load data");
			alert.setContentText("Could not load data from file:\n"
					+ file.getPath());

			alert.showAndWait();
		}
		return memberData;
	}

}
