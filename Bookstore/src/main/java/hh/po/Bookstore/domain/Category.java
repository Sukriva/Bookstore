package hh.po.Bookstore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long categoryId;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="category")
	private List <Book> books;
	
	public Category() {
		super();
		this.name=null;
	}
	
	public Category(String name) {
		super();
		this.name=name;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	@Override
	//ei tulosteta books-attribuuttia, koska ei haluta ikuista looppia
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + "]";
	}
}
