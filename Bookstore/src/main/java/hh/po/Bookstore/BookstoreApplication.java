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
import hh.po.Bookstore.domain.User;
import hh.po.Bookstore.domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			
			log.info("save books");
			crepository.save(new Category("Fantasia"));
			crepository.save(new Category("Scifi"));
			crepository.save(new Category("Kaunokirjallisuus"));
			
			repository.save(new Book("Hobitti", "Tolkien", 1973, "isbn1", 10.30, crepository.findByName("Fantasia").get(0) ));
			repository.save(new Book("Harry Potter", "Rowling", 1997, "isbn2", 12.30, crepository.findByName("Fantasia").get(0)));	
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$10$QONZsZwrXrkOPWKhmeD/e.ja4OmtVnLeOpaEDGvFpu4rqEKPUCLGG", "email1@email.com", "USER");
			User user2 = new User("admin", "$2a$10$PYL5blaYxlx3LDT5SdTfxemfLR46SbrmMMqEvK4apUVfH9dathoYq", "email2@email.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
