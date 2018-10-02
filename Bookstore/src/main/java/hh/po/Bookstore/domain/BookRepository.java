package hh.po.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepository extends CrudRepository <Book, Long>{ //luokka, Book-olioiden id:n tietotyyppi (primary keyn)
	
	List <Book> findByTitle (@Param(value="title") String title);

}
