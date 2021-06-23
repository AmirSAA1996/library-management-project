package savoir.maghreb.itis.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import savoir.maghreb.itis.model.BookEntity;
import savoir.maghreb.itis.model.QuoteEntity;

public interface QuoteRepository extends JpaRepository<QuoteEntity,Long>{
	
	// User
    Page<QuoteEntity> findByUserId(Long userId, Pageable pageable);
    Optional<QuoteEntity> findByIdAndUserId(Long id, Long userId);

}
