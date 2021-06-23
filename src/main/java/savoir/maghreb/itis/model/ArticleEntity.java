package savoir.maghreb.itis.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "article")
public class ArticleEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;  
    private String description;
    private Long theme_id;
    private Long langue_id;
    private Date date;
    private Long user_id;
    private Long user_role;
    private String references;
    private Long livre_id;
    private Long typeArticle_id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getTheme_id() {
		return theme_id;
	}
	public void setTheme_id(Long theme_id) {
		this.theme_id = theme_id;
	}
	public Long getLangue_id() {
		return langue_id;
	}
	public void setLangue_id(Long langue_id) {
		this.langue_id = langue_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getUser_role() {
		return user_role;
	}
	public void setUser_role(Long user_role) {
		this.user_role = user_role;
	}
	public String getReferences() {
		return references;
	}
	public void setReferences(String references) {
		this.references = references;
	}
	public Long getLivre_id() {
		return livre_id;
	}
	public void setLivre_id(Long livre_id) {
		this.livre_id = livre_id;
	}
	public Long getTypeArticle_id() {
		return typeArticle_id;
	}
	public void setTypeArticle_id(Long typeArticle_id) {
		this.typeArticle_id = typeArticle_id;
	}
    
    

}
