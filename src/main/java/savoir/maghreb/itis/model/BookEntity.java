package savoir.maghreb.itis.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "book")
public class BookEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;  
    @NotNull
    private String isbn;
    @NotNull
    private String title;
    @NotNull
    private String resume;
    //@NotNull 
    //private Long Category_id;
   // @NotNull 
    //private Long Langue_id;
    @NotNull 
    private Date date_publication;
    //@NotNull 
    //private Long user_id;
   // @NotNull 
   // private Long userRole_id;
    @NotNull 
    private String keyword;
    @NotNull
    private String publisher;
    @NotNull
    private Integer nbreOfpages;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="theme_id", nullable=false)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @NotNull
    private ThemeEntity theme;
    
	
    // Getters & Setters
       
    public Long getId() {
		return id;
	}

	public Date getDate_publication() {
		return date_publication;
	}

	public void setDate_publication(Date date_publication) {
		this.date_publication = date_publication;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getNbreOfpages() {
		return nbreOfpages;
	}

	public void setNbreOfpages(Integer nbreOfpages) {
		this.nbreOfpages = nbreOfpages;
	}

	public ThemeEntity getTheme() {
		return theme;
	}

	public void setTheme(ThemeEntity theme) {
		this.theme = theme;
	}

   

}
