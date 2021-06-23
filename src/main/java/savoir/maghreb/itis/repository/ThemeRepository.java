package savoir.maghreb.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import savoir.maghreb.itis.model.ThemeEntity;

public interface ThemeRepository extends JpaRepository<ThemeEntity,Long>{
	
	

}
