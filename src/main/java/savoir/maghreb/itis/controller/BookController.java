package savoir.maghreb.itis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import savoir.maghreb.itis.exception.*;
import savoir.maghreb.itis.model.BookEntity;
import savoir.maghreb.itis.model.ThemeEntity;
import savoir.maghreb.itis.repository.BookRepository;
import savoir.maghreb.itis.repository.ThemeRepository;


import java.util.Optional;

import javax.validation.Valid;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ThemeRepository themeRepository;
	
/*	@Autowired
	private CategoryRepository categoryRepository; */
	
	
	
	// GET :: Récupérer un thème et les books auquel associés
	
    @GetMapping("/themes/{themeId}/books")
    public Page<BookEntity> getAllBooksByThemeId(@PathVariable (value = "themeId") Long themeId,
                                                Pageable pageable) {
        return bookRepository.findByThemeId(themeId, pageable);
    }
    
    // POST :: Créer un book  
    
    @PostMapping("/themes/{themeId}/books")
    public BookEntity createBook(@PathVariable (value = "themeId") Long themeId,
                                 @Valid @RequestBody BookEntity book) {
        return themeRepository.findById(themeId).map(theme -> {
        	book.setTheme(theme);
            return bookRepository.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("ThemeId " + themeId + " not found"));
        
    } 
    
    // PUT :: modifier les caractéristiques du book
    
    @PutMapping("/themes/{themeId}/books/{bookId}")
    public BookEntity updateBook(@PathVariable (value = "themeId") Long themeId,
                                 @PathVariable (value = "bookId") Long bookId,
                                 @Valid @RequestBody BookEntity bookRequest) {
        if(!themeRepository.existsById(themeId)) {
            throw new ResourceNotFoundException("themeId " + themeId + " not found");
        }

        return bookRepository.findById(bookId).map(book -> {
            book.setIsbn(bookRequest.getIsbn());            
            book.setTitle(bookRequest.getTitle());
            book.setResume(bookRequest.getResume());
            book.setDate_publication(bookRequest.getDate_publication());
            book.setKeyword(bookRequest.getKeyword());
            book.setPublisher(bookRequest.getPublisher());
            book.setNbreOfpages(bookRequest.getNbreOfpages());
            //book.setTheme(bookRequest.getTheme());
            
            return bookRepository.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("BookId " + bookId + "not found"));
    }
    
    
    // DELETE :: supprimer book
    
    @DeleteMapping("/themes/{themeId}/books/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable (value = "themeId") Long themeId,
                              @PathVariable (value = "bookId") Long bookId) {
        return bookRepository.findByIdAndThemeId(bookId, themeId).map(book -> {
            bookRepository.delete(book);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + bookId + " and themeId " + themeId));
    }
    
    
    // -----------------------------------------------------------------------------
    
    //  Book et catégorie
    
	// GET :: Récupérer une categorie et les books auquel associés
    
    /*
	
    @GetMapping("/categories/{categoryId}/books")
    public Page<BookEntity> getAllBooksByCategoryId(@PathVariable (value = "categoryId") Long categoryId,
                                                Pageable pageable) {
        return bookRepository.findByCategoryId(categoryId, pageable);
    }
     
     // POST :: Créer un book tout dépend de sa categorie
    
    @PostMapping("/categories/{categoryId}/books")
    public BookEntity createBookByCategory(@PathVariable (value = "categoryId") Long categoryId,
                                 @Valid @RequestBody BookEntity book) {
        return categoryRepository.findById(categoryId).map(category -> {
        	book.setCategory(category);
            return bookRepository.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + categoryId + " not found"));
        
    }
    
    // PUT :: modifier les caractéristiques du book tout dépend de sa categorie
    
    @PutMapping("/categories/{categoryId}/books/{bookId}")
    public BookEntity updateBookByCategory(@PathVariable (value = "categoryId") Long categoryId,
                                 @PathVariable (value = "bookId") Long bookId,
                                 @Valid @RequestBody BookEntity bookRequest) {
        if(!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("CategoryId " + categoryId + " not found");
        }

        return bookRepository.findById(bookId).map(book -> {
            book.setIsbn(bookRequest.getIsbn());            
            book.setTitle(bookRequest.getTitle());
            book.setResume(bookRequest.getResume());
            book.setDate_publication(bookRequest.getDate_publication());
            book.setKeyword(bookRequest.getKeyword());
            book.setPublisher(bookRequest.getPublisher());
            book.setNbreOfpages(bookRequest.getNbreOfpages());
            //book.setTheme(bookRequest.getTheme());
            
            return bookRepository.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("BookId " + bookId + "not found"));
    }
    
    // DELETE :: supprimer book tout dépend de sa categorie
    
    @DeleteMapping("/categories/{categoryId}/books/{bookId}")
    public ResponseEntity<?> deleteBookByCategory(@PathVariable (value = "categoryId") Long categoryId,
                              @PathVariable (value = "bookId") Long bookId) {
        return bookRepository.findByIdAndThemeId(bookId, categoryId).map(book -> {
            bookRepository.delete(book);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId + " and CategoryId " + categoryId));
    }
    
    */
    // POST :: Créer un book tout dépend de sa categorie
    
 /*  @PostMapping("/themes/{themeId}/categories/{categoryId}/books")
   public String createBookByCategory(@PathVariable (value = "categoryId") Long categoryId,
		   								  @PathVariable (value = "themeId") Long themeId,
		   								  @Valid @RequestBody BookEntity book) {
	   String showBook;
	  
	   Optional<CategoryEntity> ce = categoryRepository.findById(categoryId);
       ce.map(category -> {
       	book.setCategory(category);
           return bookRepository.save(book);
       }).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + categoryId + " not found")); 
       
	   Optional<ThemeEntity> te = themeRepository.findById(themeId);
        themeRepository.findById(themeId).map(theme -> {
          	book.setTheme(theme);
              return bookRepository.save(book);
          }).orElseThrow(() -> new ResourceNotFoundException("ThemeId " + themeId + " not found"));  
        
        showBook = "theme and category added to book";
       
   return showBook; 
   } */
    
    
 //  il faut faire une méthode dans bookRepository qui regroupe tous les deux category et theme

}
