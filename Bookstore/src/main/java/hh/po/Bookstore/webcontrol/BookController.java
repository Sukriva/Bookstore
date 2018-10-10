package hh.po.Bookstore.webcontrol;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.po.Bookstore.domain.Book;
import hh.po.Bookstore.domain.BookRepository;
import hh.po.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired 
	private CategoryRepository categoryRepository;

	@RequestMapping (value="/login")
	public String login () {
		return "login";
	}
	// Lists books from database
	@RequestMapping (value="/booklist", method = RequestMethod.GET)
	public String getBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteBook (@PathVariable ("id") Long bookId) {
		//pyydetään poistamaan tietokannasta yksi kirja tietyllä id:llä
		bookRepository.deleteById(bookId);
		return "redirect:/booklist";
	}
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	//@ResposeBody, joten tämä metodi palauttaa jsonia
        return (List<Book>) bookRepository.findAll(); //haetaan tietokannasta kaikki oliot ja laitetaan ne listaan
    }    

	// RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookRepository.findById(bookId);
    }  
	//Returns an empty form for adding a new book
	@RequestMapping(value = "/add")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
	    return "addbook";
	    }  
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book){
	    bookRepository.save(book);
	    return "redirect:booklist"; //.. mennään polussa yksi ylöspäin. Tällä lauseella estetään ettei lisätä uudestaan kirjaa
	    } 
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable ("id") Long bookId, Model model){
		model.addAttribute("editbook", bookRepository.findById(bookId));
		model.addAttribute("categories", categoryRepository.findAll());
	    return "editbook";
	    }  
}
