package savoir.maghreb.itis.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import savoir.maghreb.itis.exception.ResourceNotFoundException;
import savoir.maghreb.itis.model.ThemeEntity;
import savoir.maghreb.itis.repository.ThemeRepository;

@RestController
public class ThemeController {

	@Autowired
	ThemeRepository themeRepository;
		
	// GET :: Récupérer tous les thèmes
	
    @GetMapping("/themes")
    public Page<ThemeEntity> getAllThemes(Pageable pageable) {
        return themeRepository.findAll(pageable);
    }
    
    
    // POST :: créer un thème
    
    @PostMapping("/themes")
    public ThemeEntity createTheme(@Valid @RequestBody ThemeEntity theme) {
        return themeRepository.save(theme);
    }
    
    // PUT :: modifier le contenu du theme
    
    @PutMapping("/themes/{themeId}")
    public ThemeEntity updateTheme(@PathVariable Long themeId, @Valid @RequestBody ThemeEntity themeRequest) {
        return themeRepository.findById(themeId).map(theme -> {
            theme.setName(themeRequest.getName());
            return themeRepository.save(theme);
        }).orElseThrow(() -> new ResourceNotFoundException("ThemeId " + themeId+ " not found"));
    }
    
    
    // DELETE :: supprimer un theme
    
    @DeleteMapping("/themes/{themeId}")
    public ResponseEntity<?> deletePost(@PathVariable Long themeId) {
        return themeRepository.findById(themeId).map(theme -> {
        	themeRepository.delete(theme);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ThemeId " + themeId + " not found"));
    }
	
	
	
}
