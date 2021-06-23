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
import savoir.maghreb.itis.model.UserEntity;
import savoir.maghreb.itis.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
		
	// GET :: Récupérer tous les users
	
	@GetMapping("/users")
    public Page<UserEntity> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    
    
    // POST :: créer un user
    
    @PostMapping("/users")
    public UserEntity createUser(@Valid @RequestBody UserEntity user) {
        return userRepository.save(user);
    }
    
    // PUT :: modifier le contenu du user
    
    @PutMapping("/users/{userId}")
    public UserEntity updateUser(@PathVariable Long userId, @Valid @RequestBody UserEntity userRequest) {
        return userRepository.findById(userId).map(user -> {
            user.setEmail(userRequest.getEmail());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setPassword(userRequest.getPassword());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId+ " not found"));
    }
    
    
    // DELETE :: supprimer un user
    
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
    	return userRepository.findById(userId).map(user -> {
    		userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }
		


}
