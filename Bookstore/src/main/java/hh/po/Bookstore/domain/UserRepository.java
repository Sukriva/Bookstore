package hh.po.Bookstore.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Long> { //User=luokka, Long=User-luokan id:n (primary keyn) tietotyyppi
	
	User findByUsername(String username);
}
