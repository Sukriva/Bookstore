package hh.po.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.po.Bookstore.domain.Book;
import hh.po.Bookstore.domain.BookRepository;
import hh.po.Bookstore.domain.Category;
import hh.po.Bookstore.domain.CategoryRepository;
import hh.po.Bookstore.domain.User;
import hh.po.Bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {

	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
		
	@Autowired
	private UserRepository urepository;
	
	//BookRepository tests
	//Test to find a book by title
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = brepository.findByTitle("Hobitti");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Tolkien");
	}
	
	//create a book
	@Test
	public void createNewBook() {
		Book book = new Book("Testbook", "Tester", 2018, "isbn4", 21.2, new Category("Fantasia"));
		brepository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	// delete a book
	@Test
	public void deleteBook() {
		Book book = new Book("Testing", "Tester", 2018, "isbn4", 21.2, new Category("Fantasia"));
		brepository.save(book);
		Long id = book.getId();

		brepository.deleteById(id);
		Optional<Book> b = brepository.findById(id);

		assertThat(b).isEmpty();
	}
	
	//CategoryRepository tests
	// Test to find category by name
	@Test
	public void findByNameShouldReturnCategory() {
		List<Category> cat = crepository.findByName("Fantasia");
		assertThat(cat).hasSize(1);
	}
	
	//Test to create new category
	@Test
	public void createNewCategory() {
		Category cat = new Category ("Tietokirjallisuus");
		crepository.save(cat);
		assertThat(cat.getCategoryId()).isNotNull();
	}
	
	//Test to delete category
	@Test
	public void deleteCategory() {
		Category cat = new Category ("Tietokirjallisuus");
		crepository.save(cat);
		Long id = cat.getCategoryId();

		crepository.deleteById(id);
		Optional<Category> c = crepository.findById(id);

		assertThat(c).isEmpty();
	}
	
	//UserRepository tests
	// Test to find user by username
	@Test
	public void findByUsernameShouldReturnUser() {
		User user = urepository.findByUsername("admin");
		assertThat(user).isNotNull();
	}
	
	//Test to create new user
	@Test
	public void createNewUser() {
		User user = new User ("Username", "Password", "e@mail", "User");
		urepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	//Test to delete user
	@Test
	public void deleteUser() {
		User user = new User ("Urname", "Password", "e@mail", "User");
		urepository.save(user);
		Long id = user.getId();

		urepository.deleteById(id);
		Optional<User> u = urepository.findById(id);

		assertThat(u).isEmpty();
	}
}
