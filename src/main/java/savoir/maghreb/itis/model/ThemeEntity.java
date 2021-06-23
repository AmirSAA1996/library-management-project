package savoir.maghreb.itis.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "theme")
public class ThemeEntity {

	// Attributs
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;  
    @NotNull
    private String name;
     
    @OneToMany ( targetEntity=BookEntity.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy= "theme")
    private Set<BookEntity> Books = new HashSet<>();
    
    // Getters et Setters
      
	public Long getId() {
		return id;
	}

	public Set<BookEntity> getBooks() {
		return Books;
	}

	public void setBooks(Set<BookEntity> books) {
		Books = books;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
    
	
}
