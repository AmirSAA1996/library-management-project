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
import savoir.maghreb.itis.model.QuoteEntity;
import savoir.maghreb.itis.repository.QuoteRepository;
import savoir.maghreb.itis.repository.UserRepository;

@RestController
public class QuoteController {
	
	@Autowired
	private QuoteRepository quoteRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	// GET :: Récupérer un user et les quotes auquel associés
	
    @GetMapping("/users/{userId}/quotes")
    public Page<QuoteEntity> getAllQuotesByUserId(@PathVariable (value = "userId") Long userId,
                                                Pageable pageable) {
        return quoteRepository.findByUserId(userId, pageable);
    }
    
    // POST :: Créer une citation  
    
    @PostMapping("/users/{userId}/quotes")
    public QuoteEntity createQuote(@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody QuoteEntity quote) {
        return userRepository.findById(userId).map(user -> {
        	quote.setUser(user);
            return quoteRepository.save(quote);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
        
    } 
    
    // PUT :: modifier les caractéristiques de la citation
    
    @PutMapping("/users/{userId}/quotes/{quoteId}")
    public QuoteEntity updateQuote(@PathVariable (value = "userId") Long userId,
                                 @PathVariable (value = "quoteId") Long quoteId,
                                 @Valid @RequestBody QuoteEntity quoteRequest) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UsreId " + userId + " not found");
        }

        return quoteRepository.findById(quoteId).map(quote -> {
            quote.setText(quoteRequest.getText());  
        	quote.setPublicationDate(quoteRequest.getPublicationDate()); 
            quote.setAuthor(quoteRequest.getAuthor());  
            return quoteRepository.save(quote);
        }).orElseThrow(() -> new ResourceNotFoundException("QuoteId " + quoteId + "not found"));
    }
    
    
    // DELETE :: supprimer quote
    
    @DeleteMapping("/users/{userId}/quotes/{quoteId}")
    public ResponseEntity<?> deleteBook(@PathVariable (value = "userId") Long userId,
                              @PathVariable (value = "quoteId") Long quoteId) {
        return quoteRepository.findByIdAndUserId(quoteId, userId).map(quote -> {
            quoteRepository.delete(quote);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Quote not found with id " + quoteId + " and userId " + userId));
    }

}
