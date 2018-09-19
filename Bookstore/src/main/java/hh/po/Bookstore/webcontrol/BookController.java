package hh.po.Bookstore.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.po.Bookstore.domain.Book;
import hh.po.Bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;

	@RequestMapping (value="/booklist", method = RequestMethod.GET)
	public String getBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteBook (@PathVariable ("id") Long bookId) {
		//pyydetään poistamaan tietokannasta yksi opiskelija tietyllä id:llä
		bookRepository.deleteById(bookId);
		return "redirect:/booklist";
	}
	@RequestMapping(value = "/add")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
	    return "addbook";
	    }  
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book){
	    bookRepository.save(book);
	    return "redirect:booklist"; //.. mennään polussa yksi ylöspäin. Tällä lauseella estetään ettei lisätä uudestaan studenttia
	    } 
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable ("id") Long bookId, Model model){
		model.addAttribute("editbook", bookRepository.findById(bookId));
	    return "editbook";
	    }  
}
