package hh.po.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository <Book, Long>{ //luokka, Book-olioiden id:n tietotyyppi (primary keyn)
	
	List <Book> findByTitle (String title);

}
