package savoir.maghreb.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import savoir.maghreb.itis.model.BookEntity;
import savoir.maghreb.itis.model.ThemeEntity;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long>{
	
	// theme
    Page<BookEntity> findByThemeId(Long themeId, Pageable pageable);
    Optional<BookEntity> findByIdAndThemeId(Long id, Long themeId);
    

  
}
