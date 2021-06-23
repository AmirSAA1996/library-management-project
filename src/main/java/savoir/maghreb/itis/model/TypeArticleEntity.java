package savoir.maghreb.itis.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "type_article")
public class TypeArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;  
    @NotNull
    private String typeArticle;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeArticle() {
		return typeArticle;
	}
	public void setTypeArticle(String typeArticle) {
		this.typeArticle = typeArticle;
	}
    
    
}
