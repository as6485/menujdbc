package org.ayan.menujdbc;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ayan.menujdbc.dao.BookDAO;
import org.ayan.menujdbc.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.ayan.menujdbc")
public class SpringBootConsoleApplication implements CommandLineRunner {

	@Autowired
	BookDAO bookDAO;

	private static Logger LOG = LogManager.getLogger(SpringBootConsoleApplication.class);

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(SpringBootConsoleApplication.class, args);
		LOG.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) {

		boolean flag = true;
		Scanner input = new Scanner(System.in);

		LOG.info("Total books :: " + bookDAO.countBook());

		while (flag) {

			LOG.info("1. Add Book");
			LOG.info("2. Search Book");
			LOG.info("3. Delete Book");
			LOG.info("4. Sort by title");
			LOG.info("4. Sort by Date");
			LOG.info("9. Exit Application");
			LOG.info("Enter option :: ");

			String choice = input.next();

			switch (choice) {
			case "1":
				createBook(input);
				LOG.info("Added book");
				break;
			case "2":
				searchBook(input);
				LOG.info("Searched book");
				break;
			case "3":
				deleteBook(input);
				LOG.info("Deleted book");
				break;
			case "4":
				sortByTitle();
				break;
			case "5":
				sortByDate();
				break;	
			case "9":
				LOG.info("Exiting application");
				flag = false;
				input.close();
				break;
			default:
				LOG.info("Invalid choice");

			}

		}

		input.close();

	}

	private void sortByDate() {
		bookDAO.sortByDate();
		
	}

	private void sortByTitle() {
		bookDAO.sortByTitle();
		
	}

	private void deleteBook(Scanner input) {
		Book book = new Book();
		LOG.info("Input book id to delete :: ");
		String bookid = input.next();

		bookDAO.deleteBook(Long.parseLong(bookid));

	}

	private void searchBook(Scanner input) {
		List<Book> books;
		LOG.info("Input book name to search :: ");
		String title = input.next();
		books = bookDAO.selectBook(title);

		for (Book book : books) {
			LOG.info("Found book :: " + book.getTitle());
			LOG.info("Price :: " + book.getPrice() + " Volume :: " + book.getVolume() + " Publish Date :: "
					+ book.getPublishDate());
		}
	}

	public void createBook(Scanner input) {
		Book book = new Book();
		LOG.info("Input book id :: ");
		String bookid = input.next();
		book.setBookid(Long.parseLong(bookid));
		LOG.info("Input book title :: ");
		String bookTitle = input.next();
		book.setTitle(bookTitle);

		bookDAO.addBook(book);
	}

}