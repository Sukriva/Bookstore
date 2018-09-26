package hh.po.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.po.Bookstore.domain.Book;
import hh.po.Bookstore.domain.BookRepository;
import hh.po.Bookstore.domain.Category;
import hh.po.Bookstore.domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			
			log.info("save books");
			crepository.save(new Category("Fantasia"));
			crepository.save(new Category("Scifi"));
			crepository.save(new Category("Kaunokirjallisuus"));
			
			repository.save(new Book("Hobitti", "Tolkien", 1973, "isbn1", 10.30, crepository.findByName("Fantasia").get(0) ));
			repository.save(new Book("Harry Potter", "Rowling", 1997, "isbn2", 12.30, crepository.findByName("Fantasia").get(0)));	
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
